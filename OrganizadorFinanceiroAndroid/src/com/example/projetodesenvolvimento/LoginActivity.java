/**
 * 
 */
package com.example.projetodesenvolvimento;

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

import com.example.projetodesenvolvimento.abstratas.ClasseActivity;
import com.example.projetodesenvolvimento.controladores.ControladorDeUsuario;
import com.example.projetodesenvolvimento.excecoes.Erro;
import com.example.projetodesenvolvimento.excecoes.ErroNegocio;
import com.example.projetodesenvolvimento.excecoes.SysErr;
import com.example.projetodesenvolvimento.interfaces.ClasseActivityInterface;
import com.example.projetodesenvolvimento.utils.Constantes;
import com.example.projetodesenvolvimento.utils.Dialogos;

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
		
		

		carregarEventos();
	}
	
	public void carregarEventos(){
		txtUsuario.requestFocus();
		btnLogin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				logar();
			}
		});
		
		btnCadastro.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				irPara(CadastroUsuarioActivity.class);
			}
		});
		
		txtSenha.setOnKeyListener(new OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP) {
					btnLogin.requestFocus();
//					try {
//						Thread.sleep(1000);
//						logar();
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//						Dialogos.Alerta.exibirMensagemErro(e, LoginActivity.this, null);
//					}
				} else {
					lblDadosInvalidos.setVisibility(View.GONE);
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
					//Este trecho vai demorar
//					String login = dados[0].get("login");
//					String senha = dados[0].get("senha");
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
							avisar(Constantes.DADOS_LOGIN_INVALIDOS);
							txtSenha.requestFocus();
							lblDadosInvalidos.setVisibility(View.VISIBLE);
							lblDadosInvalidos.setText(R.string.label_loginSenhaInvalidos);
						} else {
							String aviso = e.getMessage();
							aviso = aviso.replace("{", "").replace("}", "").replace("\"erro\":", "").replace("\"", "");
							Dialogos.Alerta.exibirMensagemInformacao(LoginActivity.this, false, aviso, "Atenção!", null);
						}
//						reportarErros(e);
					} else if (e instanceof SysErr) {
						String aviso = e.getMessage();
						aviso = aviso.replace("{", "").replace("}", "").replace("\"erro\":", "").replace("\"", "");
						Dialogos.Alerta.exibirMensagemInformacao(LoginActivity.this, false, aviso, "Atenção!", null);
//						avisar(aviso);
					}
				} catch (Exception e) {
					Dialogos.Progresso.fecharDialogoProgresso();
					String aviso = e.getMessage();
					aviso = aviso.replace("{", "").replace("}", "").replace("\"erro\":", "").replace("\"", "");
					Dialogos.Alerta.exibirMensagemInformacao(LoginActivity.this, false, aviso, "Atenção!", null);
					lblDadosInvalidos.setVisibility(View.VISIBLE);
					lblDadosInvalidos.setText(e.getMessage());
				}
			}
		};
		
//		new Thread(thread).start();
		new Handler(Looper.getMainLooper()).post(thread);
	}
	
	private void reportarErros(Erro e){
		String aviso = e.getMessage();
		aviso = aviso.replace("{", "").replace("}", "").replace("\"erro\":", "");
//		Dialogos.Alerta.exibirMensagemInformacao(LoginActivity.this, false, aviso, "Atenção!", null);
//		avisar(aviso);
		lblDadosInvalidos.setText(aviso);
		lblDadosInvalidos.setVisibility(View.VISIBLE);
	}
}
