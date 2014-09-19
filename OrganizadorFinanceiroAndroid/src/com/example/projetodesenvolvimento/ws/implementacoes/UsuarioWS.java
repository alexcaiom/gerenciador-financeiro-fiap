/**
 * 
 */
package com.example.projetodesenvolvimento.ws.implementacoes;

import org.json.JSONObject;

import android.content.Context;

import com.example.projetodesenvolvimento.excecoes.Erro;
import com.example.projetodesenvolvimento.orm.modelos.Usuario;
import com.example.projetodesenvolvimento.ws.WSAcoes;

/**
 * @author AlexDell
 *
 */
public class UsuarioWS extends WSAcoes {
	
	private static UsuarioWS instancia;
	private Context contexto;

	public Usuario login(String login, String senha) throws Erro{
		String acao = "login";
		Usuario usuario = null;
		try{
			JSONObject objetoJson = getJSONObject(acao, login, senha);
			usuario = new Usuario();
			preencheVo(usuario, objetoJson);
			
		}catch (Erro e){
			throw e;
		}
		
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
	
	private void preencheVo(Usuario u, JSONObject o){
		if (existe(o) && existe(u)) {
			
		}
	}
	
	public Context getContexto() {
		return contexto;
	}

	public void setContexto(Context contexto) {
		this.contexto = contexto;
	}

	public static UsuarioWS getInstancia(Context contexto){
		if (naoExiste(instancia)) {
			instancia = new UsuarioWS();
			instancia.setContexto(contexto);
		}
		return instancia;
	}
	
}
