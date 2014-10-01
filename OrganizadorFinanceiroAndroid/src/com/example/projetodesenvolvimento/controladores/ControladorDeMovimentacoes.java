/**
 * 
 */
package com.example.projetodesenvolvimento.controladores;

import java.util.List;

import com.example.projetodesenvolvimento.excecoes.Erro;
import com.example.projetodesenvolvimento.orm.modelos.Movimentacao;
import com.example.projetodesenvolvimento.servicos.ServicoMovimentacaoLocal;

import android.content.Context;

/**
 * @author Alex
 *
 */
public class ControladorDeMovimentacoes extends ControladorDeVO<Movimentacao> {

	private static ControladorDeMovimentacoes instancia;
	private Context contexto;
	
	public void cadastrar(Movimentacao m){
		
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
	
	public List<Movimentacao> pesquisarPorLogin(String login) {
		return getServicoLocal().pesquisarPorLogin(login);
	}
	
	private ServicoMovimentacaoLocal getServicoLocal(){
		return ServicoMovimentacaoLocal.getInstancia(contexto);
	}
	
	public static ControladorDeMovimentacoes getInstancia(Context contexto){
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


	public void setContexto(Context contexto) {
		this.contexto = contexto;
	}
}
