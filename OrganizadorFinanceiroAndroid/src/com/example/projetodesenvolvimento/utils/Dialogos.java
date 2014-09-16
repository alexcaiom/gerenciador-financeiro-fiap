/**
 * 
 */
package com.example.projetodesenvolvimento.utils;

import com.example.projetodesenvolvimento.abstratas.Classe;

import android.R;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;


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
			AlertDialog.Builder construtor = new AlertDialog.Builder(contexto).setIconAttribute(R.drawable.ic_dialog_info)
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
			AlertDialog.Builder construtor = new AlertDialog.Builder(contexto).setIconAttribute(R.drawable.ic_menu_zoom)
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

		private ProgressDialog dialogo;
		
		
	}

}
