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

@Path(ConstantesMapeamentoRest.MOVIMENTACAO_PESQUISA)
public class MovimentacaoPesquisa {
	@GET
	@Produces(Constantes.REST_PRODUCES)
	public Object pesquisa(@PathParam("login") String login, @PathParam("valor") String valor, @PathParam("tipo") String tipo, @PathParam("descricao") String descricao){
		Movimentacao movimentacao = new Movimentacao();
		movimentacao.setDescricao(descricao);
		movimentacao.setValor(new BigDecimal(valor));
		movimentacao.setTipo(TipoMovimento.getMovimentoPorTipo(tipo.toCharArray()[0]));
		return ServicoMovimentacao.getInstancia().pesquisar(movimentacao, login);
	}
}
