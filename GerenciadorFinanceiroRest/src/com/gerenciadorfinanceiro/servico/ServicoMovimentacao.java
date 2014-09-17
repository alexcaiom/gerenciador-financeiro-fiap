package com.gerenciadorfinanceiro.servico;

import com.gerenciadorfinanceiro.abstratas.Classe;
import com.gerenciadorfinanceiro.excecoes.Erro;
import com.gerenciadorfinanceiro.excecoes.ErroNegocio;
import com.gerenciadorfinanceiro.orm.bo.BOMovimentacao;
import com.gerenciadorfinanceiro.orm.model.EnumUsuarioAutenticado;
import com.gerenciadorfinanceiro.orm.model.Movimentacao;

public class ServicoMovimentacao extends Classe{

	private static ServicoMovimentacao instancia;
	private BOMovimentacao boMovimentacao;
	
	public String incluir(Movimentacao movimentacao, String login){
		String resposta = "";
		try{
			getBOMovimentacao().inserir(movimentacao, login);
			resposta = "Movimentação incluída com sucesso!";
		}catch(Erro e){
			if (e instanceof ErroNegocio && e.getErro().equals(EnumUsuarioAutenticado.USUARIO_INEXISTENTE.getMensagem())) {
				resposta = "Não foi possível incluir movimentações para o usuário indicado!";
			}
		}
		return resposta;
	}
	
	private BOMovimentacao getBOMovimentacao(){
		if (naoExiste(boMovimentacao)) {
			boMovimentacao = BOMovimentacao.getInstancia();
		}
		return boMovimentacao;
	}
	
	public static ServicoMovimentacao getInstancia(){
		if (naoExiste(instancia)) {
			instancia = new ServicoMovimentacao();
		}
		return instancia;
	}
	
}
