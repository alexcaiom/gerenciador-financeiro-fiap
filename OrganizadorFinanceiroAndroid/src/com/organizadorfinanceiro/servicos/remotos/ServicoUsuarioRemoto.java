/**
 * 
 */
package com.organizadorfinanceiro.servicos.remotos;

import android.content.Context;

import com.organizadorfinanceiro.abstratas.Classe;
import com.organizadorfinanceiro.excecoes.Erro;
import com.organizadorfinanceiro.excecoes.ErroNegocio;
import com.organizadorfinanceiro.excecoes.SysErr;
import com.organizadorfinanceiro.orm.modelos.Usuario;
import com.organizadorfinanceiro.utils.Constantes;
import com.organizadorfinanceiro.utils.Sessao;
import com.organizadorfinanceiro.ws.implementacoes.UsuarioWS;

/**
 * @author AlexDell
 *
 */
public class ServicoUsuarioRemoto extends Classe {

	private static ServicoUsuarioRemoto instancia;
	private Context contexto;
	
	public void logar(String login, String senha) throws Erro{
		Usuario usuario = null;
		try {
//			usuario = new Usuario(login, senha, EnumUsuarioAutenticado.SUCESSO.getCodigo());
			usuario = UsuarioWS.getInstancia(contexto).login(login, senha);
		} catch (Erro e){
			throw e;
		}
		Sessao.addParametro(Constantes.USUARIO, usuario);
	}

	public void deslogar(String login) throws Erro {
		UsuarioWS.getInstancia(contexto).logout(login);
	}

	public void cadastrar(Usuario usuario) throws Erro {
		UsuarioWS.getInstancia(contexto).cadastrar(usuario);
	}
	
	public void alterar(Usuario usuario) throws ErroNegocio, SysErr {
		UsuarioWS.getInstancia(contexto).alterar(usuario);
	}

	public void excluir(Usuario usuario) throws ErroNegocio, SysErr {
		UsuarioWS.getInstancia(contexto).excluir(usuario);
	}

	public static ServicoUsuarioRemoto getInstancia(Context contexto){
		if (naoExiste(instancia)) {
			instancia = new ServicoUsuarioRemoto();
			instancia.contexto = contexto;
		}
		return instancia;
	}
}
