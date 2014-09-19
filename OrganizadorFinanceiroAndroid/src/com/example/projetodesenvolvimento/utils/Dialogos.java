/**
 * 
 */
package com.example.projetodesenvolvimento.utils;

import com.example.projetodesenvolvimento.abstratas.Classe;
import com.example.projetodesenvolvimento.R;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.view.LayoutInflater;
import android.view.View;


/**
 * @author Alex
 *
 */
public class Dialogos extends Classe{

	public static class Alerta {
		static AlertDialog dialogo;


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
	
	static class Progresso {
		static View dialogoProcessamento = null;
		
		/**
		 * Mostra um Dialogo de Progresso de Processamento<br>
		 * <b>Lembre-se de chamar o metodo de fechamento</b>
		 * @param contexto
		 */
		public static void exibirDialogoProgresso(Context contexto){
			LayoutInflater inflador = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			dialogoProcessamento = inflador.inflate(R.layout.dialogo_progresso, null);
		}
		
		public static void fecharDialogoProgresso(){
			if (existe(dialogoProcessamento)) {
			}
		}
	}

}
