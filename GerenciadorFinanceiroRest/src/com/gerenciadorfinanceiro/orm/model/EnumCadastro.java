/**
 * 
 */
package com.gerenciadorfinanceiro.orm.model;

/**
 * @author Alex
 *
 */
public enum EnumCadastro {

	SUCESSO(0, "Cadastro realizado com sucesso!");
	
	int cod;
	String mensagem;
	/**
	 * @param cod
	 * @param mensagem
	 */
	private EnumCadastro(int cod, String mensagem) {
		this.cod = cod;
		this.mensagem = mensagem;
	}
	/**
	 * @return the cod
	 */
	public final int getCod() {
		return cod;
	}
	/**
	 * @return the mensagem
	 */
	public final String getMensagem() {
		return mensagem;
	}
	
}
