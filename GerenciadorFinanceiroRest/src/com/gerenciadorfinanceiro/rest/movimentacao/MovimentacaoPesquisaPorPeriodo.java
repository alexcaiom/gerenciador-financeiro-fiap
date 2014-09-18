package com.gerenciadorfinanceiro.rest.movimentacao;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.gerenciadorfinanceiro.rest.ConstantesMapeamentoRest;
import com.gerenciadorfinanceiro.servico.ServicoMovimentacao;
import com.gerenciadorfinanceiro.utils.Constantes;

@Path(ConstantesMapeamentoRest.MOVIMENTACAO_PESQUISA_POR_PERIODO)
public class MovimentacaoPesquisaPorPeriodo {
	@GET
	@Produces(Constantes.REST_PRODUCES)
	public Object pesquisaPorPeriodo(@PathParam("login") String login, @PathParam("dataDe") String dataDe, @PathParam("dataAte") String dataAte){
		return ServicoMovimentacao.getInstancia().pesquisarPorPeriodo(login, dataDe, dataAte);
	}
}
