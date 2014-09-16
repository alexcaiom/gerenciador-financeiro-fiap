/**
 * 
 */
package com.gerenciadorfinanceiro.orm.dao;

import com.gerenciadorfinanceiro.orm.model.usuario.UsuarioContato;

/**
 * @author Alex
 *
 */
public class DAOUsuarioContato extends GenericDAO<UsuarioContato>{

	public DAOUsuarioContato() {
		super(UsuarioContato.class);
		// TODO Auto-generated constructor stub
	}

	private static DAOUsuarioContato instancia;

	public static DAOUsuarioContato getInstancia(){
		if(instancia == null){
			instancia = new DAOUsuarioContato();
		}
		return instancia;
	}
}
