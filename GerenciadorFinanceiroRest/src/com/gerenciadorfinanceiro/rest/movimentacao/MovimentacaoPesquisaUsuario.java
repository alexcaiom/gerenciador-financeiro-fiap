package com.gerenciadorfinanceiro.rest.movimentacao;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.gerenciadorfinanceiro.rest.ConstantesMapeamentoRest;
import com.gerenciadorfinanceiro.servico.ServicoMovimentacao;
import com.gerenciadorfinanceiro.utils.Constantes;

@Path(ConstantesMapeamentoRest.MOVIMENTACAO_PESQUISA_USUARIO)
public class MovimentacaoPesquisaUsuario {
	@GET
	@Produces(Constantes.REST_PRODUCES)
	
	public Object pesquisarPorUsuario(@PathParam("login") String login){
		return ServicoMovimentacao.getInstancia().pesquisarPorUsuario(login);
	}
}
