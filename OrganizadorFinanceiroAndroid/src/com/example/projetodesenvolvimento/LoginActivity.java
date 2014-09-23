/**
 * 
 */
package com.example.projetodesenvolvimento;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.projetodesenvolvimento.abstratas.ClasseActivity;
import com.example.projetodesenvolvimento.excecoes.ErroNegocio;
import com.example.projetodesenvolvimento.excecoes.SysErr;
import com.example.projetodesenvolvimento.interfaces.ClasseActivityInterface;
import com.example.projetodesenvolvimento.servicos.remotos.ServicoUsuarioRemoto;
import com.example.projetodesenvolvimento.utils.Constantes;
import com.example.projetodesenvolvimento.utils.Dialogos;

/**
 * @author AlexDell
 *
 */
public class LoginActivity extends ClasseActivity  implements ClasseActivityInterface{
	
	private static final Class DESTINO = MenuActivity.class;
	Button btnLogin;
	EditText txtUsuario,  txtSenha;
	TextView lblDadosInvalidos;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		carregarTela();
	}
	
	public void carregarTela(){
		btnLogin = (Button) findViewById(R.id.btnLogin);
		txtUsuario = (EditText) findViewById(R.id.txtLogin);
		txtSenha = (EditText) findViewById(R.id.txtSenha);
		lblDadosInvalidos = (TextView) findViewById(R.id.lblLoginInvalido);
		carregarEventos();
	}
	
	public void carregarEventos(){
		btnLogin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				logar();
			}
		});
		
		txtSenha.setOnKeyListener(new OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_ENTER) {
					logar();
				} else {
					lblDadosInvalidos.setVisibility(View.GONE);
				}
				return false;
			}
		});
	}

	private void logar() {
		String login = txtUsuario.getText().toString();
		String senha = txtSenha.getText().toString();
		try {
			Dialogos.Progresso.exibirDialogoProgresso(this, this);
			ServicoUsuarioRemoto.logar(login, senha);
			Dialogos.Progresso.fecharDialogoProgresso();
			irPara(DESTINO);
		} catch (ErroNegocio e) {
			/**
			 * Aqui podemos ter um login invalido
			 */
			if (e.getMessage().equals(Constantes.DADOS_LOGIN_INVALIDOS)) {
				avisar(Constantes.DADOS_LOGIN_INVALIDOS);
				txtSenha.requestFocus();
				lblDadosInvalidos.setVisibility(View.VISIBLE);
			}
		} catch (SysErr e) {
			Dialogos.Alerta.exibirMensagemErro(e, LoginActivity.this, null);
		}
	}

}
