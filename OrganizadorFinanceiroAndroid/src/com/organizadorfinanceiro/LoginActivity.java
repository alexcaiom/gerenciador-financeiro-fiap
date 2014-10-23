/**
 * 
 */
package com.organizadorfinanceiro;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.organizadorfinanceiro.abstratas.ClasseActivity;
import com.organizadorfinanceiro.controladores.ControladorDeUsuario;
import com.organizadorfinanceiro.excecoes.Erro;
import com.organizadorfinanceiro.excecoes.ErroNegocio;
import com.organizadorfinanceiro.excecoes.SysErr;
import com.organizadorfinanceiro.interfaces.ClasseActivityInterface;
import com.organizadorfinanceiro.utils.Constantes;
import com.organizadorfinanceiro.utils.Dialogos;

/**
 * @author AlexDell
 *
 */
public class LoginActivity extends ClasseActivity  implements ClasseActivityInterface{
	
	private static final Class DESTINO = MenuActivity.class;
	Button btnLogin, btnCadastro;
	EditText txtUsuario,  txtSenha;
	TextView lblDadosInvalidos;
	Handler handler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		carregarTela();
	}
	
	public void carregarTela(){
		btnLogin = (Button) findViewById(R.id.login_btnLogin);
		btnCadastro = (Button) findViewById(R.id.login_btnCadastro);
		txtUsuario = (EditText) findViewById(R.id.txtLogin);
		txtSenha = (EditText) findViewById(R.id.txtSenha);
		lblDadosInvalidos = (TextView) findViewById(R.id.lblLoginInvalido);
		ocultarBarraDeAcoes();
		carregarEventos();
	}
	
	public void carregarEventos(){
		txtUsuario.requestFocus();
		btnLogin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (txtUsuario.getText().toString().isEmpty()
						|| txtSenha.getText().toString().isEmpty()) {
					exibirLabelErroLoginComMensagem("Login e senha devem ser preenchidos.");
					return;
				}
				logar();
			}
		});
		
		btnCadastro.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				irPara(CadastroUsuarioActivity.class);
			}
		});
		
		txtUsuario.setOnKeyListener(new OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				ocultarLabelErro();
				return false;
			}
		});
		
		txtSenha.setOnKeyListener(new OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				lblDadosInvalidos.setVisibility(View.GONE);
				if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP) {
					btnLogin.requestFocus();
				} else {
					ocultarLabelErro();
				}
				return false;
			}
		});
	}

	private void logar() {
		final String login = txtUsuario.getText().toString();
		final String senha = txtSenha.getText().toString();
		
		Dialogos.Progresso.exibirDialogoProgresso(this);
		Runnable thread = new Runnable() {
			public void run() {
				try {
					ControladorDeUsuario.getInstancia(LoginActivity.this).login(login, senha);
					Dialogos.Progresso.fecharDialogoProgresso();
					irPara(DESTINO);
					} catch (Erro e) {
						Dialogos.Progresso.fecharDialogoProgresso();
						if (e instanceof ErroNegocio) {
							/**
							 * Aqui podemos ter um login invalido
							 */
							if (e.getMessage().equals(Constantes.DADOS_LOGIN_INVALIDOS)) {
								Dialogos.Alerta.exibirMensagemInformacao(LoginActivity.this, false, e.getMessage(), "Atenção!", null);
								txtSenha.requestFocus();
								exibirLabelErroLoginComMensagem(Constantes.DADOS_LOGIN_INVALIDOS);
							} else {
								String aviso = e.getMessage();
								aviso = aviso.replace("{", "").replace("}", "").replace("erro", "").replace("\"", "").replace(":", "");
								Dialogos.Alerta.exibirMensagemInformacao(LoginActivity.this, false, aviso, "Atenção!", null);
							}
						} else if (e instanceof SysErr) {
							Dialogos.Alerta.exibirMensagemErro(e, LoginActivity.this, null);
							Dialogos.Alerta.fecharDialogo();
						}
					} catch (Exception e) {
						Dialogos.Progresso.fecharDialogoProgresso();
						Dialogos.Alerta.exibirMensagemErro(e, LoginActivity.this, null);
					}
			}
		};
		
//		new Thread(thread).start();
		new Handler(Looper.getMainLooper()).post(thread);
		
	}
	
	private void exibirLabelErroLoginComMensagem(String texto){
		lblDadosInvalidos.setBackgroundColor(Color.RED);
		lblDadosInvalidos.setTextColor(Color.WHITE);
		lblDadosInvalidos.setText(texto);
		lblDadosInvalidos.setVisibility(View.VISIBLE);
	}
	
	private void ocultarLabelErro(){
		lblDadosInvalidos.setVisibility(View.GONE);
	}
	
}
