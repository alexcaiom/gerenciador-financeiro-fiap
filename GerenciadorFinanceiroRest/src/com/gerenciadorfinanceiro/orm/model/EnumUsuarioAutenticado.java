/**
 * 
 */
package com.gerenciadorfinanceiro.orm.model;

/**
 * @author Alex
 *
 */
public enum EnumUsuarioAutenticado {

	SUCESSO(0, "Te encontramos!"),
	USUARIO_INEXISTENTE(1, "Usuário inexistente"),
	SENHA_INVALIDA(2, "Senha inválida");
	
	int cod;
	String mensagem;
	
	private EnumUsuarioAutenticado(int cod, String mensagem) {
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
