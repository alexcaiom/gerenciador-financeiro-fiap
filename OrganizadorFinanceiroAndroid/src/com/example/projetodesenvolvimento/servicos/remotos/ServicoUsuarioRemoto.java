/**
 * 
 */
package com.example.projetodesenvolvimento.servicos.remotos;

import android.content.Context;

import com.example.projetodesenvolvimento.abstratas.Classe;
import com.example.projetodesenvolvimento.enums.EnumUsuarioAutenticado;
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
	private Context contexto;
	
	public void logar(String login, String senha) throws Erro{
		Usuario usuario = null;
//		try {
			usuario = new Usuario(login, senha, EnumUsuarioAutenticado.SUCESSO.getCodigo());
//			usuario = UsuarioWS.getInstancia(contexto).login(login, senha);
//		} catch (Erro e){
//			throw e;
//		}
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
