package com.gerenciadorfinanceiro.rest.movimentacao;

import java.math.BigDecimal;
import java.util.GregorianCalendar;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.gerenciadorfinanceiro.abstratas.Classe;
import com.gerenciadorfinanceiro.orm.model.Movimentacao;
import com.gerenciadorfinanceiro.orm.model.enums.TipoMovimento;
import com.gerenciadorfinanceiro.rest.ConstantesMapeamentoRest;
import com.gerenciadorfinanceiro.servico.ServicoMovimentacao;
import com.gerenciadorfinanceiro.utils.UtilsData;

@Path(ConstantesMapeamentoRest.MOVIMENTACAO_CADASTRO)
public class MovimentacaoCadastro extends Classe{
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Object incluir(@PathParam("login") String login, @PathParam("valor") String valor, 
						  @PathParam("tipo") String tipo, @PathParam("descricao") String descricao,
						  @PathParam("data") String data){
		Movimentacao movimentacao = new Movimentacao();
		movimentacao.setDescricao(descricao);
		movimentacao.setValor(new BigDecimal(valor));
		movimentacao.setTipo(TipoMovimento.getMovimentoPorTipo(tipo.toCharArray()[0]));
		if (existe(data)) {
			try {
				movimentacao.setData(UtilsData.strToCalendar(data));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			movimentacao.setData(GregorianCalendar.getInstance());
		}
		return ServicoMovimentacao.getInstancia().incluir(movimentacao, login);
	}
}
