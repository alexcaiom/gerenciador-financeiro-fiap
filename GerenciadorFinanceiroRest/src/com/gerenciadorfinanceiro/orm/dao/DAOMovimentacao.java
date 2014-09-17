/**
 * 
 */
package com.gerenciadorfinanceiro.orm.dao;

import com.gerenciadorfinanceiro.orm.model.Movimentacao;

/**
 * @author Alex
 *
 */
public class DAOMovimentacao extends GenericDAO<Movimentacao> {

	public DAOMovimentacao() {
		super(Movimentacao.class);
	}

	private static DAOMovimentacao instancia;

	public static DAOMovimentacao getInstancia(){
		if(instancia == null){
			instancia = new DAOMovimentacao();
		}
		return instancia;
	}
}
