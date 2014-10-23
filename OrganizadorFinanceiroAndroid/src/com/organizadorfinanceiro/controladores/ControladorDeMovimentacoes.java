/**
 * 
 */
package com.organizadorfinanceiro.controladores;

import java.util.List;

import android.content.Context;

import com.organizadorfinanceiro.abstratas.ClasseActivity;
import com.organizadorfinanceiro.excecoes.Erro;
import com.organizadorfinanceiro.facade.FacadeMovimentacao;
import com.organizadorfinanceiro.orm.modelos.Movimentacao;
import com.organizadorfinanceiro.orm.modelos.enums.TipoMovimento;

/**
 * @author Alex
 *
 */
public class ControladorDeMovimentacoes extends ControladorDeVO<Movimentacao> {

	private static ControladorDeMovimentacoes instancia;
	private ClasseActivity contexto;
	
	public void excluir(Movimentacao movimentacao) throws Erro {
		FacadeMovimentacao.getInstancia(contexto).excluir(movimentacao);		
	}
	
	public void gravar(Movimentacao movimentacao) throws Erro {
		boolean deveCadastrar = naoExiste(movimentacao.getCodigo());
		boolean deveAlterar = existe(movimentacao.getCodigo());
		if (deveCadastrar) {
			FacadeMovimentacao.getInstancia(contexto).cadastrar(movimentacao);
		}
		if (deveAlterar) {
			FacadeMovimentacao.getInstancia(contexto).alterar(movimentacao);
		}
	}
	
	public List<Movimentacao> pesquisarPorLoginETipoMovimento(String login, TipoMovimento tipo, Long dataInicioPesquisa, Long dataFimPesquisa) throws Erro {
		return FacadeMovimentacao.getInstancia(contexto).pesquisarPorLoginETipoMovimento(login, tipo, dataInicioPesquisa, dataFimPesquisa);
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
