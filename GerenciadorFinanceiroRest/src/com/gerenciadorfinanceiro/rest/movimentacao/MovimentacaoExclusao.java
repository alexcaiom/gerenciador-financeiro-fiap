package com.gerenciadorfinanceiro.rest.movimentacao;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.gerenciadorfinanceiro.abstratas.Classe;
import com.gerenciadorfinanceiro.orm.model.Movimentacao;
import com.gerenciadorfinanceiro.rest.ConstantesMapeamentoRest;
import com.gerenciadorfinanceiro.servico.ServicoMovimentacao;

@Path(ConstantesMapeamentoRest.MOVIMENTACAO_EXCLUSAO)
public class MovimentacaoExclusao extends Classe{
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Object incluir(@PathParam("login") String login, @PathParam("codigo") String codigo){
		Movimentacao movimentacao = new Movimentacao();
		if (!codigo.isEmpty()) {
			movimentacao.setCodigo(Long.parseLong(codigo));
		} else {
			return "Codigo invalido";
		}
		return ServicoMovimentacao.getInstancia().alterar(movimentacao, login);
	}
}
