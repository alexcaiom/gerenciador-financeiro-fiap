/**
 * 
 */
package com.example.projetodesenvolvimento.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

import com.example.projetodesenvolvimento.R;
import com.example.projetodesenvolvimento.abstratas.Classe;
import com.example.projetodesenvolvimento.abstratas.ClasseActivity;
import com.example.projetodesenvolvimento.abstratas.ClasseFragmentoActivity;


/**
 * @author Alex
 *
 */
public class Dialogos extends Classe{

	public static class Alerta {
		static AlertDialog dialogo;

		/**
		 * Exibe mensagem de Alerta.<br/>
		 * <b>Lembre-se de chamar o metodo fecharDialogo()</b>
		 * @param contexto
		 * @param cancelavel
		 * @param mensagem
		 * @param titulo
		 * @param escutadorOk
		 */
		public static void exibirMensagemInformacao(Context contexto, boolean cancelavel, 
				CharSequence mensagem, CharSequence titulo, 
				OnClickListener escutadorOk){
			AlertDialog.Builder construtor = new AlertDialog.Builder(contexto).setIconAttribute(android.R.drawable.ic_dialog_info)
					.setCancelable(cancelavel)
					.setMessage(mensagem)
					.setTitle(titulo)
					.setPositiveButton("Ok!", escutadorOk);

			(dialogo = construtor.create()).show();

		}
		public static void exibirMensagemPergunta(Context contexto, boolean cancelavel, 
				CharSequence mensagem, CharSequence titulo, 
				OnClickListener comportamentoSim, OnClickListener comportamentoNao, 
				OnClickListener comportamentoCancelar){
			AlertDialog.Builder construtor = new AlertDialog.Builder(contexto).setIconAttribute(android.R.drawable.ic_menu_zoom)
					.setCancelable(cancelavel)
					.setMessage(mensagem)
					.setTitle(titulo)
					.setPositiveButton("Sim", comportamentoSim)
					.setNeutralButton("Cancelar", comportamentoCancelar)
					.setNegativeButton("Não", comportamentoNao);

			(dialogo = construtor.create()).show();

		}

		public static void fecharDialogo(){
			dialogo.dismiss();
		}

		public static void exibirMensagemErro(Throwable e, Context contexto, OnClickListener acao) {
			exibirMensagemInformacao(contexto, false, "Um erro ocorreu: \n"+e.getMessage(), "Ocorreu um erro durante a operação", acao);
		}
	}
	
	public static class Progresso {
		static View dialogoProcessamento = null;
		static Activity telaAlvo = null;
		
		/**
		 * Mostra um Dialogo de Progresso de Processamento<br>
		 * <b>Lembre-se de chamar o metodo de fechamento</b>
		 * @param contexto
		 */
		public static void exibirDialogoProgresso(ClasseActivity tela){
			telaAlvo = tela;
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
				LayoutInflater inflador = (LayoutInflater) tela.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				dialogoProcessamento = inflador.inflate(R.layout.dialogo_progresso, null);
				tela.addContentView(dialogoProcessamento, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
				int shortAnimTime = tela.getResources().getInteger(android.R.integer.config_shortAnimTime);
				dialogoProcessamento.setVisibility(View.VISIBLE);
				dialogoProcessamento.animate().setDuration(shortAnimTime)
					.alpha(1)
					.setListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation) {
							dialogoProcessamento.setVisibility(View.VISIBLE);
						}
				});
			}
		}
		
		/**
		 * Mostra um Dialogo de Progresso de Processamento<br>
		 * <b>Lembre-se de chamar o metodo de fechamento</b>
		 * @param contexto
		 */
		public static void exibirDialogoProgresso(ClasseFragmentoActivity tela){
			telaAlvo = tela;
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
				LayoutInflater inflador = (LayoutInflater) tela.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				dialogoProcessamento = inflador.inflate(R.layout.dialogo_progresso, null);
				tela.addContentView(dialogoProcessamento, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
				int shortAnimTime = tela.getResources().getInteger(android.R.integer.config_shortAnimTime);
				dialogoProcessamento.setVisibility(View.VISIBLE);
				dialogoProcessamento.animate().setDuration(shortAnimTime)
				.alpha(1)
				.setListener(new AnimatorListenerAdapter() {
					@Override
					public void onAnimationEnd(Animator animation) {
						dialogoProcessamento.setVisibility(View.VISIBLE);
					}
				});
			}
		}
		
		public static void fecharDialogoProgresso(){
			if (existe(dialogoProcessamento)) {
				int shortAnimTime = telaAlvo.getResources().getInteger(android.R.integer.config_shortAnimTime);
				dialogoProcessamento.animate().setDuration(shortAnimTime)
				.alpha(0)
				.setListener(new AnimatorListenerAdapter() {
					@Override
					public void onAnimationEnd(Animator animation) {
						dialogoProcessamento.setVisibility(View.GONE);
					}
			});
			}
		}
	}

}
