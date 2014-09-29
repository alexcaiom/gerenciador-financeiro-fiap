/**
 * 
 */
package com.example.projetodesenvolvimento.orm.dao.interfaces;

import com.example.projetodesenvolvimento.excecoes.SysErr;

/**
 * @author Alex
 *
 */
public interface IGenericDAO<T> {

	public T incluir(T orm) throws SysErr;
	public void atualizar(T orm) throws SysErr;
	public void excluir(T orm) throws SysErr;

}
