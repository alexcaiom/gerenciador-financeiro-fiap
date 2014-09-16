/**
 * 
 */
package com.gerenciadorfinanceiro.orm.dao;

import com.gerenciadorfinanceiro.orm.model.usuario.UsuarioSocial;

/**
 * @author Alex
 *
 */
public class DAOUsuarioSocial extends GenericDAO<UsuarioSocial>  {
	
	public DAOUsuarioSocial() {
		super(UsuarioSocial.class);
	}

	private static DAOUsuarioSocial instancia;

	public static DAOUsuarioSocial getInstancia(){
		if(instancia == null){
			instancia = new DAOUsuarioSocial();
		}
		return instancia;
	}
}
