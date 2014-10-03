/**
 * 
 */
package com.example.projetodesenvolvimento.controladores;

import java.util.List;

import android.content.Context;

import com.example.projetodesenvolvimento.abstratas.ClasseActivity;
import com.example.projetodesenvolvimento.excecoes.Erro;
import com.example.projetodesenvolvimento.excecoes.SysErr;
import com.example.projetodesenvolvimento.orm.modelos.Movimentacao;
import com.example.projetodesenvolvimento.orm.modelos.enums.TipoMovimento;
import com.example.projetodesenvolvimento.servicos.ServicoMovimentacaoLocal;

/**
 * @author Alex
 *
 */
public class ControladorDeMovimentacoes extends ControladorDeVO<Movimentacao> {

	private static ControladorDeMovimentacoes instancia;
	private ClasseActivity contexto;
	
	public void cadastrar(Movimentacao m){
		
	}
	
	public void excluir(Movimentacao movimentacao) throws SysErr {
		getServicoLocal().excluir(movimentacao);		
	}
	
	public void gravar(Movimentacao movimentacao) throws Erro {
		boolean deveCadastrar = naoExiste(movimentacao.getCodigo());
		boolean deveAlterar = existe(movimentacao.getCodigo());
		if (deveCadastrar) {
			getServicoLocal().cadastrar(movimentacao);
		}
		if (deveAlterar) {
			getServicoLocal().alterar(movimentacao);
		}
	}
	
	public List<Movimentacao> pesquisarPorLoginETipoMovimento(String login, TipoMovimento tipo, Long dataInicioPesquisa, Long dataFimPesquisa) {
		return getServicoLocal().pesquisarPorLoginETipoMovimento(login, tipo, dataInicioPesquisa, dataFimPesquisa);
	}
	
	private ServicoMovimentacaoLocal getServicoLocal(){
		return ServicoMovimentacaoLocal.getInstancia(contexto);
	}
	
	public static ControladorDeMovimentacoes getInstancia(ClasseActivity contexto){
		if (naoExiste(instancia)) {
			instancia = new ControladorDeMovimentacoes();
			instancia.setContexto(contexto);
		}
		return instancia;
	}


	@Override
	void encriptaVO(Movimentacao o) {
		// TODO Auto-generated method stub
		
	}


	public Context getContexto() {
		return contexto;
	}


	public void setContexto(ClasseActivity contexto) {
		this.contexto = contexto;
	}
}
