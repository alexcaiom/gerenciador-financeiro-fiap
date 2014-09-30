/**
 * 
 */
package com.example.projetodesenvolvimento.abstratas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.projetodesenvolvimento.CadastroUsuarioActivity;
import com.example.projetodesenvolvimento.LoginActivity;
import com.example.projetodesenvolvimento.SplashActivity;
import com.example.projetodesenvolvimento.controladores.ControladorDeUsuario;
import com.example.projetodesenvolvimento.excecoes.Erro;
import com.example.projetodesenvolvimento.excecoes.ErroNegocio;
import com.example.projetodesenvolvimento.excecoes.SysErr;
import com.example.projetodesenvolvimento.interfaces.ClasseActivityInterface;
import com.example.projetodesenvolvimento.orm.modelos.Usuario;
import com.example.projetodesenvolvimento.utils.Constantes;
import com.example.projetodesenvolvimento.utils.Dialogos;
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
//		verificaSessao();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
//		verificaSessao();
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
	
	public void deslogar(){
		final Usuario usuarioLogado = (Usuario) Sessao.getParametro(Constantes.USUARIO);
		Dialogos.Progresso.exibirDialogoProgresso(this);
		Runnable thread = new Runnable() {
			public void run() {
				try {
					//Este trecho vai demorar
					ControladorDeUsuario.getInstancia(ClasseActivity.this).logout(usuarioLogado.getLogin());
					Dialogos.Progresso.fecharDialogoProgresso();
				} catch (Erro e) {
					if (e instanceof ErroNegocio) {
						Dialogos.Progresso.fecharDialogoProgresso();
					} else if (e instanceof SysErr) {
						Dialogos.Alerta.exibirMensagemErro(e, ClasseActivity.this, null);
						Dialogos.Alerta.fecharDialogo();
					}
				} catch (Exception e) {
					Dialogos.Progresso.fecharDialogoProgresso();
					Dialogos.Alerta.exibirMensagemErro(e, ClasseActivity.this, null);
				}
			}
		};
		
		new Thread(thread).start();
		Sessao.deslogar();
		verificaSessao();
	}
	
	/**
	 * Metodo que verifica se o objeto eh nulo
	 * @param o
	 * @return
	 */
	public static boolean naoExiste(Object o){
		boolean naoExiste = Classe.naoExiste(o);
		return naoExiste;
	}
	
	/**
	 * Metodo que verifica se o objeto nao eh nulo
	 * @param o
	 * @return
	 */
	public static boolean existe(Object o){
		boolean existe = !naoExiste(o);
		return existe;
	}
	
	public void ocultarBarraDeAcoes(){
		getActionBar().hide();
	}
}
