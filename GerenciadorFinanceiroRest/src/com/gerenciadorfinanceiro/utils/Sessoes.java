/**
 * 
 */
package com.gerenciadorfinanceiro.utils;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import com.gerenciadorfinanceiro.orm.model.usuario.Usuario;

/**
 * @author Alex
 *
 */
public class Sessoes {

	private static final Map<String, SessaoUsuario> sessoesAtivas = new HashMap<String, SessaoUsuario>();
	
	public synchronized static final SessaoUsuario getUsuarioLogado(String login){
		return sessoesAtivas.get(login);
	}
	
	public synchronized static final void addSessao(Usuario usuario){
		SessaoUsuario sessao = new SessaoUsuario(usuario.getLogin(), usuario, GregorianCalendar.getInstance());
		sessoesAtivas.put(usuario.getLogin(), sessao);
	}
	
	public synchronized static final boolean temSessao(String login){
		return sessoesAtivas.containsKey(login);
	}
	
	public synchronized static final boolean usuarioLogado(String login){
		return temSessao(login) && getUsuarioLogado(login) != null;
	}
	
	public synchronized static final void removerSessao(String login){
		sessoesAtivas.remove(login);
	}
	
	public synchronized static final void expirarSessao(String login){
		if (usuarioLogado(login)) {
			removerSessao(login);
		}
	}
}
