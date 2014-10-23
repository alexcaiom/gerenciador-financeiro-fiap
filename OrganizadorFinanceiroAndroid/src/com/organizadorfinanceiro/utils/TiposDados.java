/**
 * 
 */
package com.organizadorfinanceiro.utils;

/**
 * @author Alex
 *
 */
public enum TiposDados {

	TEXTO("text"),
	INTEIRO("int");
	
	String tipo;
	
	TiposDados(String tipo) {
		this.tipo = tipo;
	}
	
	public String getTipo(){
		return this.tipo;
	}
	
}
