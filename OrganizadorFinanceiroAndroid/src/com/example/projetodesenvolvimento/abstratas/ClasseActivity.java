/**
 * 
 */
package com.example.projetodesenvolvimento.abstratas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.projetodesenvolvimento.CadastroUsuarioActivity;
import com.example.projetodesenvolvimento.LoginActivity;
import com.example.projetodesenvolvimento.SplashActivity;
import com.example.projetodesenvolvimento.interfaces.ClasseActivityInterface;
import com.example.projetodesenvolvimento.utils.Sessao;

/**
 * @author Alex
 *
 */
public abstract class ClasseActivity extends Activity implements ClasseActivityInterface{

	public final String CLASSE_NOME = getClass().getSimpleName();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		verificaSessao();
	}

	public void irPara(Class tela, Bundle parametros){
		Intent intencao = new Intent(this, tela);
		if(parametros != null && !parametros.isEmpty()){
			intencao.putExtras(parametros);
		}
		startActivity(intencao);
	}
	
	public void irPara(Class tela){
		irPara(tela, null);
	}
	
	/**
	 * Metodo que lanca um aviso do tipo Toast ao usuario<br/>
	 * Aviso padrao: Longo
	 * @param aviso
	 */
	public void avisar(String aviso, Integer duracao) {
		if(duracao == null){
			Toast.makeText(getApplicationContext(), aviso, Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(getApplicationContext(), aviso, duracao).show();
		}
	}

	/**
	 * Metodo que lanca um aviso do tipo Toast ao usuario<br/>
	 * Aviso padrao: Longo
	 * @param aviso
	 */
	public void avisar(String aviso) {
		avisar(aviso, null);
	}
	
	private void verificaSessao() {
		if (!Sessao.usuarioLogado()) {
			if (!this.getClass().getSimpleName().equalsIgnoreCase(LoginActivity.class.getSimpleName()) && 
					!this.getClass().getSimpleName().equalsIgnoreCase(CadastroUsuarioActivity.class.getSimpleName()) &&
					!this.getClass().getSimpleName().equalsIgnoreCase(SplashActivity.class.getSimpleName())) {
				irPara(LoginActivity.class);
			}
		}
	}
	
	public void exibirMensagemDeProcessamento(){
		
	}
	
	protected void log(Object textoParaLog) {
		Log.i(CLASSE_NOME, textoParaLog.toString());
	}
	
}
