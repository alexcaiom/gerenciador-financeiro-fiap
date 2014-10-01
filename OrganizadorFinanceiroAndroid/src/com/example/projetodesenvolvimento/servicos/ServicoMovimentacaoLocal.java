package com.example.projetodesenvolvimento.servicos;

import java.util.List;

import com.example.projetodesenvolvimento.abstratas.Classe;
import com.example.projetodesenvolvimento.excecoes.Erro;
import com.example.projetodesenvolvimento.orm.bo.BOMovimentacao;
import com.example.projetodesenvolvimento.orm.modelos.Movimentacao;

import android.content.Context;

public class ServicoMovimentacaoLocal extends Classe {

	private static ServicoMovimentacaoLocal instancia;
	private Context contexto;
	
	public void cadastrar(Movimentacao o) throws Erro{
		getBO().inserir(o);
	}
	
	public void alterar(Movimentacao o) throws Erro {
		getBO().alterar(o);
	}
	
	public List<Movimentacao> pesquisarPorLogin(String login) {
		return getBO().pesquisarPorLogin(login);
	}
	
	public static ServicoMovimentacaoLocal getInstancia(Context contexto){
		if (naoExiste(instancia)) {
			instancia = new ServicoMovimentacaoLocal();
			instancia.contexto = contexto;
		}
		return instancia;
	}
	
	private BOMovimentacao getBO(){
		return BOMovimentacao.getInstancia(contexto);
	}
	
}
