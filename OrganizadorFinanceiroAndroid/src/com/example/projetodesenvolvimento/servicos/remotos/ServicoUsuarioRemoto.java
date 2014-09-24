/**
 * 
 */
package com.example.projetodesenvolvimento.servicos.remotos;

import android.content.Context;

import com.example.projetodesenvolvimento.abstratas.Classe;
import com.example.projetodesenvolvimento.excecoes.Erro;
import com.example.projetodesenvolvimento.excecoes.ErroNegocio;
import com.example.projetodesenvolvimento.excecoes.SysErr;
import com.example.projetodesenvolvimento.orm.modelos.Usuario;
import com.example.projetodesenvolvimento.utils.Constantes;
import com.example.projetodesenvolvimento.utils.Sessao;
import com.example.projetodesenvolvimento.ws.implementacoes.UsuarioWS;

/**
 * @author AlexDell
 *
 */
public class ServicoUsuarioRemoto extends Classe {

	private static ServicoUsuarioRemoto instancia;
	private static Context contexto;
	
	public static void logar(String login, String senha) throws Erro{
		Usuario usuario = null;
		try {
			usuario = new UsuarioWS().login(login, senha);
		} catch (Erro e){
			throw e;
		}
		Sessao.addParametro(Constantes.USUARIO, usuario);
	}

	public static void cadastrar(Usuario usuario) throws Erro {
		UsuarioWS.getInstancia(contexto).cadastrar(usuario);
	}
	
	public static void alterar(Usuario usuario) throws ErroNegocio, SysErr {
		new UsuarioWS().alterar(usuario);
	}

	public static void excluir(Usuario usuario) throws ErroNegocio, SysErr {
		new UsuarioWS().excluir(usuario);
	}

	public static ServicoUsuarioRemoto getInstancia(Context contexto){
		if (naoExiste(instancia)) {
			instancia = new ServicoUsuarioRemoto();
			instancia.contexto = contexto;
		}
		return instancia;
	}
	
}
