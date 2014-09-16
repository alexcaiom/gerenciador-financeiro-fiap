package com.gerenciadorfinanceiro.orm.model;


public enum RedeSocial {

	FACEBOOK		(1, 'F', "Facebook"),
	TWITTER			(2, 'T', "Twitter"), 
	INSTAGRAM		(3, 'I', "Instagram"),
	LINKEDIN		(4, 'L', "LinkedIn"),
	GOOGLE_PLUS		(5, 'G', "Google+");
	
	private int cod;
	private String descricao;
	private char sigla;
	
	private RedeSocial(int cod, char sigla, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
		this.sigla = sigla;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public char getSigla(){
		return sigla;
	}
	
	public static RedeSocial getRedeSocial(int cod){
		for (RedeSocial r : values()) {
			if(r.getCod() == cod){
				return r;
			}
		}
		return null;
	}
	
	public static RedeSocial getRedeSocial(char sigla){
		for (RedeSocial r : values()) {
			if(r.getSigla() == sigla){
				return r;
			}
		}
		return null;
	}
	@Override
	public String toString() {
		return getDescricao();
	}
}
