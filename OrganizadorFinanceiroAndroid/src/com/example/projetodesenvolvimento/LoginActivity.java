/**
 * 
 */
package com.example.projetodesenvolvimento;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.projetodesenvolvimento.abstratas.ClasseActivity;
import com.example.projetodesenvolvimento.excecoes.Erro;
import com.example.projetodesenvolvimento.excecoes.ErroNegocio;
import com.example.projetodesenvolvimento.excecoes.SysErr;
import com.example.projetodesenvolvimento.interfaces.ClasseActivityInterface;
import com.example.projetodesenvolvimento.servicos.ServicoUsuario;
import com.example.projetodesenvolvimento.utils.Constantes;
import com.example.projetodesenvolvimento.utils.Dialogos;

/**
 * @author AlexDell
 *
 */
public class LoginActivity extends ClasseActivity  implements ClasseActivityInterface{
	
	Button btnLogin;
	EditText txtUsuario,  txtSenha;
	
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
		carregarEventos();
	}
	
	public void carregarEventos(){
		btnLogin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String login = txtUsuario.getText().toString();
				String senha = txtSenha.getText().toString();
				try {
					logar(login, senha);
					irPara(MainActivity.class);
				} catch (ErroNegocio e) {
					/**
					 * Aqui podemos ter um login invalido
					 */
					if (e.getMessage().equals(Constantes.DADOS_LOGIN_INVALIDOS)) {
						avisar(Constantes.DADOS_LOGIN_INVALIDOS);
					}
				} catch (SysErr e) {
					Dialogos.Alerta.exibirMensagemErro(e, LoginActivity.this, null);
				}
			}
		});
	}

	protected void logar(String login, String senha) throws ErroNegocio, SysErr{
		ServicoUsuario.logar(login, senha);
	}

}
