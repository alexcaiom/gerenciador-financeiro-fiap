/**
 * 
 */
package com.example.projetodesenvolvimento.servicos;

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
public class ServicoUsuario {

	public static void logar(String login, String senha) throws ErroNegocio, SysErr{
		//Usuario usuario = new UsuarioWS().login(login, senha);
		Usuario usuario = new Usuario(login, senha, 0);
		Sessao.addParametro(Constantes.USUARIO, usuario);
	}

	public static void cadastrar(Usuario usuario) throws ErroNegocio, SysErr {
		new UsuarioWS().cadastrar(usuario);
	}
	
	public static void alterar(Usuario usuario) throws ErroNegocio, SysErr {
		new UsuarioWS().alterar(usuario);
	}

	public static void excluir(Usuario usuario) throws ErroNegocio, SysErr {
		new UsuarioWS().excluir(usuario);
	}
	
}
