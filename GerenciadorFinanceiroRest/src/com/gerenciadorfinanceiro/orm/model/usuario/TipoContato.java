/**
 * 
 */
package com.gerenciadorfinanceiro.orm.model.usuario;

/**
 * @author Alex
 *
 */
public enum TipoContato {

	TELEFONE_RESIDENCIAL(1, "Telefone Residencial"),
	TELEFONE_CELULAR(2, "Telefone Celular"),
	TELEFONE_COMERCIAL(3, "Telefone Comercial"),
	E_MAIL(4, "E-mail");
	
	int cod;
	String descricao;
	
	TipoContato(int cod, String descricao){
		
	}
}
