/**
 * 
 */
package com.gerenciadorfinanceiro.orm.dao;

import com.gerenciadorfinanceiro.orm.model.usuario.Usuario;

/**
 * @author Alex
 *
 */
public class DAOUsuario extends GenericDAO<Usuario> {

	public DAOUsuario() {
		super(Usuario.class);
	}

	private static DAOUsuario instancia;

	public static DAOUsuario getInstancia(){
		if(instancia == null){
			instancia = new DAOUsuario();
		}
		return instancia;
	}
}
