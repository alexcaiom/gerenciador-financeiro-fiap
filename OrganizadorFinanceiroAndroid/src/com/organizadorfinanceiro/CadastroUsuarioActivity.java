package com.organizadorfinanceiro;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.organizadorfinanceiro.abstratas.ClasseActivity;
import com.organizadorfinanceiro.controladores.ControladorDeUsuario;
import com.organizadorfinanceiro.excecoes.Erro;
import com.organizadorfinanceiro.excecoes.ErroNegocio;
import com.organizadorfinanceiro.excecoes.SysErr;
import com.organizadorfinanceiro.utils.Dialogos;

public class CadastroUsuarioActivity extends ClasseActivity {

	EditText 	txtNome, txtEmail, txtLogin, txtSenha, txtConfirmaSenha;
	Button 		btnCadastrar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastro_usuario);
		carregarTela();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cadastro_usuario, menu);
		return true;
	}

	@Override
	public void carregarTela() {
		txtNome 			= (EditText) 	findViewById(R.id.txtUsuario_nome);
		txtEmail 			= (EditText) 	findViewById(R.id.txtUsuario_email);
		txtLogin 			= (EditText) 	findViewById(R.id.txtUsuario_login);
		txtSenha 			= (EditText) 	findViewById(R.id.txtUsuario_senha);
		txtConfirmaSenha 	= (EditText) 	findViewById(R.id.txtUsuario_confirmaSenha);
		btnCadastrar		= (Button) 		findViewById(R.id.usuario_btnCadastrar);
		carregarEventos();
	}

	@Override
	public void carregarEventos() {
		btnCadastrar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				cadastrar();
			}
		});
	}

	private void cadastrar() {
		String nome 				= txtNome.getText().toString();
		String email 				= txtEmail.getText().toString();
		String login 				= txtLogin.getText().toString();
		String senha 				= txtSenha.getText().toString();
		String confirmacaoSenha 	= txtConfirmaSenha.getText().toString();
		
		boolean camposDeSenhaEConfirmacaoDeSenhaSaoCorrespondentes = verificaSeSenhaEConfimacaoDeSenhaSaoIguais(senha, confirmacaoSenha);
		if (camposDeSenhaEConfirmacaoDeSenhaSaoCorrespondentes) {
			try {
				ControladorDeUsuario.getInstancia(this).cadastrar(nome, email, login, senha);
				irPara(MenuActivity.class);
			} catch (Erro e) {
				if (e instanceof ErroNegocio) {

					/**
					 * Aqui podemos ter um login invalido
					 */
					String aviso = e.getMessage();
					if (aviso.contains("{") || aviso.contains("}")) {
						aviso = aviso.replace("{\"erro\"", "").replace("}", "");
					}
					avisar(aviso);
				} else if (e instanceof SysErr) {
					Dialogos.Alerta.exibirMensagemErro(e, CadastroUsuarioActivity.this, null);
				}
			} catch (Exception e) {
				Dialogos.Alerta.exibirMensagemErro(e, CadastroUsuarioActivity.this, null);
			} finally {
				Dialogos.Progresso.fecharDialogoProgresso();
			}
			
		} else {
			avisar("Senha e Confirmacao de Senha nao conferem!");
			txtSenha.requestFocus();
		}
		
	}
	
	private boolean verificaSeSenhaEConfimacaoDeSenhaSaoIguais(String senha,
			String confirmacaoSenha) {
		return senha.equals(confirmacaoSenha);
	}

}
