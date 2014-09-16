/**
 * 
 */
package com.example.projetodesenvolvimento.ws.implementacoes;

import org.json.JSONObject;

import com.example.projetodesenvolvimento.excecoes.SysErr;
import com.example.projetodesenvolvimento.orm.modelos.Usuario;
import com.example.projetodesenvolvimento.ws.WSAcoes;

/**
 * @author AlexDell
 *
 */
public class UsuarioWS extends WSAcoes {

	public Usuario login(String login, String senha) throws SysErr{
		JSONObject objetoJson = getUsuario(login, senha);
		
		Usuario usuario = new Usuario();
		return usuario;
	}

	public JSONObject getUsuario(String usuario, String senha) {
		// TODO Auto-generated method stub
		return null;
	}

	public void cadastrar(Usuario usuario) {
		// TODO Auto-generated method stub
		
	}

	public void alterar(Usuario usuario) {
		// TODO Auto-generated method stub
		
	}

	public void excluir(Usuario usuario) {
		// TODO Auto-generated method stub
		
	}
	
}
