/**
 * 
 */
package com.organizadorfinanceiro.orm.dao.interfaces;

import com.organizadorfinanceiro.excecoes.SysErr;

/**
 * @author Alex
 *
 */
public interface IGenericDAO<T> {

	public T incluir(T orm) throws SysErr;
	public void atualizar(T orm) throws SysErr;
	public void excluir(T orm) throws SysErr;

}
