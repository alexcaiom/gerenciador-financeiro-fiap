package com.gerenciadorfinanceiro.rest;

import java.math.BigDecimal;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.gerenciadorfinanceiro.orm.model.Movimentacao;
import com.gerenciadorfinanceiro.orm.model.enums.TipoMovimento;
import com.gerenciadorfinanceiro.servico.ServicoMovimentacao;
import com.gerenciadorfinanceiro.servico.ServicoUsuario;

@Path("/movimentacao/cadastro/{login}/{email}/{senha}")
public class MovimentacaoCadastro {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Object incluir(@PathParam("login") String login, @PathParam("valor") String valor, @PathParam("tipo") String tipo){
		Movimentacao movimentacao = new Movimentacao();
		movimentacao.setValor(new BigDecimal(valor));
		movimentacao.setTipo(TipoMovimento.getMovimentoPorTipo(tipo.toCharArray()[0]));
		return ServicoMovimentacao.getInstancia().incluir(movimentacao, login);
	}
}
