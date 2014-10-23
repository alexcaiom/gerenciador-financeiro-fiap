/**
 * 
 */
package com.organizadorfinanceiro.facade;

import android.content.Context;

import com.organizadorfinanceiro.abstratas.Classe;
import com.organizadorfinanceiro.excecoes.Erro;
import com.organizadorfinanceiro.orm.modelos.Usuario;
import com.organizadorfinanceiro.servicos.remotos.ServicoUsuarioRemoto;

/**
 * @author Alex
 *
 */
public class FacadeUsuario extends Classe {

	private static FacadeUsuario instancia;
	private Context contexto;
	
	public void logar(String login, String senha) throws Erro{
		ServicoUsuarioRemoto.getInstancia(contexto).logar(login, senha);
	}

	public void deslogar(String login) throws Erro {
		ServicoUsuarioRemoto.getInstancia(contexto).deslogar(login);
	}

	public void cadastrar(Usuario usuario) throws Erro {
		ServicoUsuarioRemoto.getInstancia(contexto).cadastrar(usuario);		
	}
	
	public static FacadeUsuario getInstancia(Context contexto){
		if (naoExiste(instancia)) {
			instancia = new FacadeUsuario();
			instancia.contexto = contexto;
		}
		return instancia;
	}
}
