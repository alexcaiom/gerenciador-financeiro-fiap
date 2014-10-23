/**
 * 
 */
package com.organizadorfinanceiro.facade;

import java.util.ArrayList;
import java.util.List;

import com.organizadorfinanceiro.abstratas.Classe;
import com.organizadorfinanceiro.abstratas.ClasseActivity;
import com.organizadorfinanceiro.excecoes.Erro;
import com.organizadorfinanceiro.orm.modelos.Movimentacao;
import com.organizadorfinanceiro.orm.modelos.enums.TipoMovimento;
import com.organizadorfinanceiro.servicos.ServicoMovimentacaoLocal;
import com.organizadorfinanceiro.servicos.remotos.ServicoMovimentacaoRemoto;

/**
 * @author Alex <br/>
 * Classe que verifica se o dispositivo esta conectado e
 * chama os servicos local e remoto para realizar as operacoes
 * de seus metodos.
 */
public class FacadeMovimentacao extends Classe {

	private static FacadeMovimentacao instancia;
	private ClasseActivity contexto;
	
	public void cadastrar(Movimentacao movimentacao) throws Erro {
		boolean estaConectado = temInternet();
		if (estaConectado) {
			getServicoRemoto().cadastrar(movimentacao);
		}
		getServicoLocal().cadastrar(movimentacao);
	}
	
	public void alterar(Movimentacao movimentacao) throws Erro {
		boolean estaConectado = temInternet();
		if (estaConectado) {
			getServicoRemoto().alterar(movimentacao);
		}
		getServicoLocal().alterar(movimentacao);		
	}
	

	public void excluir(Movimentacao movimentacao) throws Erro {
		boolean estaConectado = temInternet();
		if (estaConectado) {
			getServicoRemoto().excluir(movimentacao);
		}
		getServicoLocal().excluir(movimentacao);
	}

	public List<Movimentacao> pesquisarPorLoginETipoMovimento(String login,
			TipoMovimento tipo, Long dataInicioPesquisa, Long dataFimPesquisa) throws Erro {
		boolean estaConectado = temInternet();
		List<Movimentacao> movimentacoes = new ArrayList<Movimentacao>();
		if (estaConectado) {
			movimentacoes = getServicoRemoto().pesquisarPorLoginETipoMovimento(login,
					tipo, dataInicioPesquisa, dataFimPesquisa);
		} else {
			movimentacoes = getServicoLocal().pesquisarPorLoginETipoMovimento(login,
					tipo, dataInicioPesquisa, dataFimPesquisa);
		}
			
		return movimentacoes;
	}
	
	public void pesquisarPorId(Movimentacao movimentacao) throws Erro {
		ServicoMovimentacaoRemoto.getInstancia(contexto).cadastrar(movimentacao);		
	}
	
	public static FacadeMovimentacao getInstancia(ClasseActivity contexto){
		if (naoExiste(instancia)) {
			instancia = new FacadeMovimentacao();
			instancia.contexto = contexto;
		}
		return instancia;
	}
	
	private ServicoMovimentacaoLocal getServicoLocal(){
		return ServicoMovimentacaoLocal.getInstancia(contexto);
	}
	
	private ServicoMovimentacaoRemoto getServicoRemoto(){
		return ServicoMovimentacaoRemoto.getInstancia(contexto);
	}

	private boolean temInternet(){
		return true;
	}

}
