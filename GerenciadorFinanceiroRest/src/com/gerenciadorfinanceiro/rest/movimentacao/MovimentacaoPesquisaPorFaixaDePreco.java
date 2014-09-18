package com.gerenciadorfinanceiro.rest.movimentacao;

import java.math.BigDecimal;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.gerenciadorfinanceiro.orm.model.Movimentacao;
import com.gerenciadorfinanceiro.orm.model.enums.TipoMovimento;
import com.gerenciadorfinanceiro.rest.ConstantesMapeamentoRest;
import com.gerenciadorfinanceiro.servico.ServicoMovimentacao;
import com.gerenciadorfinanceiro.utils.Constantes;

@Path(ConstantesMapeamentoRest.MOVIMENTACAO_PESQUISA_POR_FAIXA_DE_PRECO)
public class MovimentacaoPesquisaPorFaixaDePreco {
	@GET
	@Produces(Constantes.REST_PRODUCES)
	public Object pesquisaPorFaixaDePreco(@PathParam("login") String login, @PathParam("valorDe") String valorDe, @PathParam("valorAte") String valorAte){
		return ServicoMovimentacao.getInstancia().pesquisarPorFaixaDePreco(login, valorDe, valorAte);
	}
}
