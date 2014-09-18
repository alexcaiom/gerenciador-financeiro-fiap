package com.gerenciadorfinanceiro.vos;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.gerenciadorfinanceiro.orm.model.Movimentacao;

@XmlRootElement(name="lista")
public class ListaMovimentacao extends ArrayList<Movimentacao> {

	public ListaMovimentacao() {}
	public ListaMovimentacao(List<Movimentacao> movimentacoes){
		super(movimentacoes);
	}
	
}
