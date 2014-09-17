/**
 * 
 */
package com.gerenciadorfinanceiro.orm.model;

/**
 * @author Alex
 *
 */
public enum EnumUsuarioCadastrado {

	SUCESSO(0, "Seja Bem Vindo!"),
	USUARIO_DUPLICADO(1, "Usuario ja existente!"),
	SENHA_INVALIDA(2, "Senha inválida"),
	ERRO_AO_CRIAR_USUARIO(3, "Ocorreu um erro ao criar o usuário");
	
	int cod;
	String mensagem;
	
	private EnumUsuarioCadastrado(int cod, String mensagem) {
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
