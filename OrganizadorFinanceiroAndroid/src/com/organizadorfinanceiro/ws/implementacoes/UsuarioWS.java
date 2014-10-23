/**
 * 
 */
package com.organizadorfinanceiro.ws.implementacoes;

import org.json.JSONObject;

import android.content.Context;

import com.organizadorfinanceiro.excecoes.Erro;
import com.organizadorfinanceiro.orm.modelos.Usuario;
import com.organizadorfinanceiro.utils.Constantes;
import com.organizadorfinanceiro.utils.UtilsData;
import com.organizadorfinanceiro.ws.WSAcoes;

/**
 * @author AlexDell
 *
 */
public class UsuarioWS extends WSAcoes {
	
	private static UsuarioWS instancia;
	private Context contexto;
	private final String entidade = "usuario";

	public Usuario login(String login, String senha) throws Erro{
		String acao = "login";
		Usuario usuario = null;
		try{
			JSONObject objetoJson = getJSONObject(acao, entidade, login, senha);
			usuario = new Usuario();
			preencheVo(usuario, objetoJson);
			
		}catch (Erro e){
			throw e;
		} catch (Exception e) {
			throw new Erro(e);
		}
		
		return usuario;
	}
	

	public void logout(String login) throws Erro {
		String acao = "logout";
		try{
			url = Constantes.CONEXAO_PROTOCOLO+"://"+enderecoServidor+"/"+Constantes.CONEXAO_CONTEXTO+"/"+entidade+"/"+acao+"/"+login;
			comunicarComOServidor(url);
		} catch (Exception e){
			throw new Erro(e);
		}
	}

	public void cadastrar(Usuario usuario) throws Erro {
		String acao = "cadastro";
		try{
			String nome = usuario.getNome();
			String email = usuario.getEmail();
			String login = usuario.getLogin();
			String senha = usuario.getSenha();
			JSONObject objetoJson = getJSONObject(acao, entidade, login, email, senha);
			usuario = new Usuario();
			preencheVo(usuario, objetoJson);
			
		}catch (Erro e){
			throw e;
		} catch (Exception e) {
			throw new Erro(e);
		}
	}

	public void alterar(Usuario usuario) {
		// TODO Auto-generated method stub
		
	}

	public void excluir(Usuario usuario) {
		// TODO Auto-generated method stub
		
	}
	
	private void preencheVo(Usuario u, JSONObject o) throws Exception{
		if (existe(o) && existe(u)) {
			u.comLogin(o.getString("login"));
			u.comSenha(o.getString("senha"));
//			u.estaBloqueado(o.get)
			u.setNome(o.getString("nome"));
			u.setDataDeNascimento(UtilsData.strToCalendar(getJSonStringSeExistir(o, "dataNascimento")));
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
