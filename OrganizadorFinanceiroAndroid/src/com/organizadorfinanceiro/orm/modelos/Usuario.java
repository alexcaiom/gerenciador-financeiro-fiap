/**
 * 
 */
package com.organizadorfinanceiro.orm.modelos;

import java.util.GregorianCalendar;

/**
 * @author AlexDell
 *
 */
public class Usuario extends Pessoa {
	
	private String login;
	private String senha;
	private int bloqueado;
	
	public Usuario() {}
	public Usuario(String login, String senha, int bloqueado) {
		super();
		this.login = login;
		this.senha = senha;
		this.bloqueado = bloqueado;
	}
	
	public Usuario(String login, String senha, int bloqueado, String nome, Integer idade, GregorianCalendar dataDeNascimento, String email){
		super(nome, idade, dataDeNascimento, email);
		this.login = login;
		this.senha = senha;
		this.bloqueado = bloqueado;
	}


	public String getLogin() {
		return login;
	}
	public Usuario comLogin(String login) {
		this.login = login;
		return this;
	}
	public String getSenha() {
		return senha;
	}
	public Usuario comSenha(String senha) {
		this.senha = senha;
		return this;
	}
	public int getBloqueado() {
		return bloqueado;
	}
	public Usuario estaBloqueado(int bloqueado) {
		this.bloqueado = bloqueado;
		return this;
	}
	
}
