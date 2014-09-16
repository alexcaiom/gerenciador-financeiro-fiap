/**
 * 
 */
package com.gerenciadorfinanceiro.utils;

/**
 * @author Alex
 *
 */
public class Constantes {

	/**
	 * BANCO DE DADOS
	 */
	public static final String BANCO_DADOS_NOME = "myIP.db";
	public static final int BANCO_DADOS_VERSAO = 1;
	
	/**
	 * OPERACOES
	 */
	public static final String USUARIO = "login";
	public static final String DISCAR_PARA = "Discar para ";
	public static final String ENVIAR_MENSAGEM_PARA = "Enviar mensagem para ";
	
	
	public static final String ITEM_AMIGO = "amigo";
	
	/**
	 * MASCARAS
	 */
	public static final String MASK_CELULAR = "(##)#-####-####";
	public static final String ESCUTADOR = "ESCUTADOR";
	
	
	/**
	 * Constantes de Operacao
	 */
	public static final String OPERACAO_INCLUSAO = "Inclusão";
	public static final String OPERACAO_ATUALIZACAO = "Atualização";
	public static final String OPERACAO_EXCLUSAO = "Exclusão";
	public static final String OPERACAO_PESQUISA = "Pesquisa";
	
	/**
	 * CONSTANTES DE MENSAGEM
	 */
	public static final String MENSAGEM_SUCESSO = "A {operacao} do {item} foi realizada com sucesso!";
	public static final String MENSAGEM_FALHA = "A {operacao} do {item} falhou! Tente mais tarde!";
	
	public static final String getMensagemSucesso(Class qualVO, String operacao){
		return MENSAGEM_SUCESSO.replace("{operacao}", operacao).replace("{item}", qualVO.getSimpleName());
	}

	public static final String getMensagemFalha(Class qualVO, String operacao){
		return MENSAGEM_SUCESSO.replace("{operacao}", operacao).replace("{item}", qualVO.getSimpleName());
	}

}
