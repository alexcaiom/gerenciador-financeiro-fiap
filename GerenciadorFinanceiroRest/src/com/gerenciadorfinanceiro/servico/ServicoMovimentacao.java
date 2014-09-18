package com.gerenciadorfinanceiro.servico;

import com.gerenciadorfinanceiro.abstratas.Classe;
import com.gerenciadorfinanceiro.excecoes.Erro;
import com.gerenciadorfinanceiro.excecoes.ErroNegocio;
import com.gerenciadorfinanceiro.excecoes.ErroVO;
import com.gerenciadorfinanceiro.excecoes.SysErr;
import com.gerenciadorfinanceiro.orm.bo.BOMovimentacao;
import com.gerenciadorfinanceiro.orm.model.EnumUsuarioAutenticado;
import com.gerenciadorfinanceiro.orm.model.Movimentacao;
import com.gerenciadorfinanceiro.utils.Sessoes;

public class ServicoMovimentacao extends Classe{

	private static ServicoMovimentacao instancia;
	private BOMovimentacao boMovimentacao;
	
	public String incluir(Movimentacao movimentacao, String login){
		String resposta = "";
		try{
			if (verificaLogin(login)) {
				getBOMovimentacao().inserir(movimentacao, login);
				resposta = "Movimentação incluída com sucesso!";
			} else {
				resposta = "Sessão expirada! Logue-se novamente.";
			}
		}catch(Erro e){
			if (e instanceof ErroNegocio && e.getErro().equals(EnumUsuarioAutenticado.USUARIO_INEXISTENTE.getMensagem())) {
				resposta = "Não foi possível incluir movimentações para o usuário indicado!";
			}
		}
		return resposta;
	}
	
	public Object pesquisar(Movimentacao movimentacao, String login) {
		try{
			if (verificaLogin(login)) {
				return getBOMovimentacao().pesquisar(movimentacao); 
			} else {
				return "Sessão expirada! Logue-se novamente.";
			}
		}catch(ErroNegocio e){
			return new ErroVO(e);
		}catch (SysErr e) {
			return new ErroVO(e);
		}
	}
	
	public Object pesquisarPorUsuario(String login) {
		try{
			if (verificaLogin(login)) {
				return getBOMovimentacao().pesquisarPorUsuario(login); 
			} else {
				return "Sessão expirada! Logue-se novamente.";
			}
		}catch(ErroNegocio e){
			return new ErroVO(e);
		}catch (SysErr e) {
			return new ErroVO(e);
		}
	}
	
	public Object pesquisarPorFaixaDePreco(String login, String valorDe,
			String valorAte) {
		try{
			if (verificaLogin(login)) {
				return getBOMovimentacao().pesquisarPorFaixaDePreco(valorDe, valorAte); 
			} else {
				return "Sessão expirada! Logue-se novamente.";
			}
		}catch(ErroNegocio e){
			return new ErroVO(e);
		}catch (SysErr e) {
			return new ErroVO(e);
		}
	}
	
	public Object pesquisarPorPeriodo(String login, String dataDe,
			String dataAte) {
		try{
			if (verificaLogin(login)) {
				return getBOMovimentacao().pesquisarPorPeriodo(dataDe, dataAte); 
			} else {
				return "Sessão expirada! Logue-se novamente.";
			}
		}catch(ErroNegocio e){
			return new ErroVO(e);
		}catch (SysErr e) {
			return new ErroVO(e);
		}
	}
	
	private boolean verificaLogin(String login){
		return Sessoes.temSessao(login);
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
