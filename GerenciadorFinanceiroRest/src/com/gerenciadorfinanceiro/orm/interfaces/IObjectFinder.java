/**
 * 
 */
package com.gerenciadorfinanceiro.orm.interfaces;

import java.util.List;


/**
 * @author Alex
 *
 */
public interface IObjectFinder<T> {
	
	public T find(Long id) ;
	public List<T> findByUsuario(Long id) ;
	public List<T> listar() ;
	
}
