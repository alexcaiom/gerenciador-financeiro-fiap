/**
 * 
 */
package com.gerenciadorfinanceiro.orm.bo;

import java.util.List;

import com.gerenciadorfinanceiro.abstratas.Classe;
import com.gerenciadorfinanceiro.excecoes.Erro;

/**
 * @author Alex
 *
 */
public abstract class BO<T> extends Classe {

	/**
	 * Metodos gravar estao preparados para realizar a seguinte rotina:<br/>
	 * Rolar a lista<br/>
	 *  - Condicao (Objeto ja existente?)<br/>
	 *  -- > Caso sim, o objeto eh atualizado<br/>
	 *  -- > Caso nao, o objeto eh inserido<br/>
	 * @param o
	 * @throws Erro
	 */
	public abstract void gravar(List<T> o) throws Erro;
	
	/**
	 * Metodos de Exclusao sao preparados para realizar exclusao de Listas<br/>
	 * de Objetos
	 * @param o
	 * @throws Erro
	 */
	public abstract void excluir(List<T> o) throws Erro;
	
	public String getNomeEntidade(){
		return CLASSE_NOME.substring(2);
	}
	
}
