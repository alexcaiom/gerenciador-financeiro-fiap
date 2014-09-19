package com.example.projetodesenvolvimento;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.projetodesenvolvimento.abstratas.ClasseActivity;
import com.example.projetodesenvolvimento.controladores.ControladorDeUsuario;
import com.example.projetodesenvolvimento.enums.EnumUsuarioAutenticado;
import com.example.projetodesenvolvimento.excecoes.ErroNegocio;
import com.example.projetodesenvolvimento.orm.modelos.Usuario;
import com.example.projetodesenvolvimento.ws.implementacoes.UsuarioWS;

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
				ControladorDeUsuario.getInstancia(this).cadastrar(nome, email, login, senha, confirmacaoSenha);
			} catch (ErroNegocio e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
