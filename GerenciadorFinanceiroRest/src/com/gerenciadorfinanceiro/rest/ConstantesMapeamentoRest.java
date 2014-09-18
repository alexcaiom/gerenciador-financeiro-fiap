package com.gerenciadorfinanceiro.rest;

public class ConstantesMapeamentoRest {
	
	/**
	 * Constantes de Usuario
	 */
	public static final String USUARIO_CADASTRO 						= "/usuario/cadastro/{login}/{email}/{senha}";
	public static final String USUARIO_ATUALIZACAO 						= "/usuario/atualizacao/{login}/{nome}/{sobrenome}/{email}/{dataNascimento}/{senha}/{contadorSenhaInvalida}/{status}";
	public static final String USUARIO_LISTA 							= "/usuario/lista";
	public static final String USUARIO_LOGIN 							= "/usuario/login/{login}/{senha}";
	public static final String USUARIO_LOGOUT 							= "/usuario/logout/{login}";
	public static final String USUARIO_PESQUISAR_POR_ID 				= "/usuario/pesquisarPorID/{id}";
	public static final String USUARIO_PESQUISAR_POR_LOGIN_COMO 		= "/usuario/pesquisarPorLoginComo/{login}";
	public static final String USUARIO_VERIFICA_SESSAO 					= "/usuario/verificaSessao/{login}/{senha}";
	
	/**
	 * Constantes de Movimentacao
	 */
	public static final String MOVIMENTACAO_CADASTRO 					= "/movimentacao/cadastro/{login}/{valor}/{tipo}/{descricao}/{data}";
	public static final String MOVIMENTACAO_LISTAGEM 					= "/movimentacao/lista/{login}";
	public static final String MOVIMENTACAO_PESQUISA 					= "/movimentacao/pesquisa/{login}/{valor}/{tipo}/{descricao}";
	public static final String MOVIMENTACAO_PESQUISA_POR_FAIXA_DE_PRECO = "/movimentacao/pesquisaPorFaixaDePreco/{login}/{valorDe}/{valorAte}";
	public static final String MOVIMENTACAO_PESQUISA_POR_PERIODO		= "/movimentacao/pesquisaPorPeriodo/{login}/{dataDe}/{dataAte}";
	public static final String MOVIMENTACAO_PESQUISA_USUARIO 			= "/movimentacao/pesquisa/{login}";
	
	
}
