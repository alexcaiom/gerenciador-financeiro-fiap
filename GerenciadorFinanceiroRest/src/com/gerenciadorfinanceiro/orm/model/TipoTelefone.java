/**
 * 
 */
package com.gerenciadorfinanceiro.orm.model;


/**
 * @author Alex
 *
 */
public enum TipoTelefone {

	CELULAR(0, "Celular"),
	CASA(0, "Casa"),
	TRABALHO(0, "Trabalho");
	
	int cod;
	String tipo;
	String mensagem;
	
	TipoTelefone(int cod, String tipo){
		this.cod = cod;
		this.tipo = tipo;
	}

	public int getCod() {
		return cod;
	}

	public String getTipo() {
		return tipo;
	}

	public String getMensagem() {
		return mensagem;
	}
	
}
