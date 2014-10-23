package com.organizadorfinanceiro.servicos.remotos;

import java.util.List;

import com.organizadorfinanceiro.abstratas.Classe;
import com.organizadorfinanceiro.abstratas.ClasseActivity;
import com.organizadorfinanceiro.excecoes.Erro;
import com.organizadorfinanceiro.orm.modelos.Movimentacao;
import com.organizadorfinanceiro.orm.modelos.enums.TipoMovimento;
import com.organizadorfinanceiro.ws.implementacoes.MovimentacaoWS;

public class ServicoMovimentacaoRemoto extends Classe {

	private static ServicoMovimentacaoRemoto instancia;
	private ClasseActivity contexto;
	
	public void cadastrar(Movimentacao o) throws Erro{
		//getBO().inserir(o);
		MovimentacaoWS.getInstancia(contexto).cadastrar(o);
	}
	
	public void alterar(Movimentacao o) throws Erro {
		//getBO().alterar(o);
		MovimentacaoWS.getInstancia(contexto).alterar(o);
	}
	
	public void excluir(Movimentacao o) throws Erro {
		//getBO().excluir(movimentacao);
		MovimentacaoWS.getInstancia(contexto).excluir(o);
	}
	
	
	public List<Movimentacao> pesquisarPorLoginETipoMovimento(String login, TipoMovimento tipo, Long dataInicioPesquisa, Long dataFimPesquisa) throws Erro {
		//return getBO().pesquisarPorLoginETipoMovimento(login, tipo, dataInicioPesquisa, dataFimPesquisa);
		return MovimentacaoWS.getInstancia(contexto).pesquisarPorLoginETipoMovimento(login, tipo, dataInicioPesquisa, dataFimPesquisa);
	}
	
	public static ServicoMovimentacaoRemoto getInstancia(ClasseActivity contexto){
		if (naoExiste(instancia)) {
			instancia = new ServicoMovimentacaoRemoto();
			instancia.contexto = contexto;
		}
		return instancia;
	}
}
