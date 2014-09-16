/**
 * 
 */
package com.example.projetodesenvolvimento.orm.modelos;

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
	
	public Usuario(String login, String senha, int bloqueado, String nome, Integer idade, GregorianCalendar dataDeNascimento){
		super(nome, idade, dataDeNascimento);
		this.login = login;
		this.senha = senha;
		this.bloqueado = bloqueado;
	}


	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public int getBloqueado() {
		return bloqueado;
	}
	public void setBloqueado(int bloqueado) {
		this.bloqueado = bloqueado;
	}
	
}
