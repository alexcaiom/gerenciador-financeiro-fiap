package com.gerenciadorfinanceiro.rest.movimentacao;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.gerenciadorfinanceiro.rest.ConstantesMapeamentoRest;
import com.gerenciadorfinanceiro.servico.ServicoMovimentacao;
import com.gerenciadorfinanceiro.utils.Constantes;

@Path(ConstantesMapeamentoRest.MOVIMENTACAO_LISTAGEM)
public class MovimentacaoListagem {
	@GET
	@Produces(Constantes.REST_PRODUCES)
	public Object listar(@PathParam("login") String login){
		return ServicoMovimentacao.getInstancia().pesquisarPorUsuario(login);
	}
}
