package com.organizadorfinanceiro.orm.modelos;

public class Categoria {

	private String descricao;
	private String login;
	
	public Categoria() {}
	
	public Categoria(String descricao, String login) {
		this.descricao = descricao;
		this.login = login;
	}

	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
}
