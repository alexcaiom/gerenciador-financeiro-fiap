package com.gerenciadorfinanceiro.orm.dao;


public class Finder<T> extends GenericDAO<T>{

	public Finder(Class<T> entityClass) {
		super(entityClass);
	}

	protected String getNomeEntidade(){
		return CLASSE_NOME.substring(CLASSE_NOME.lastIndexOf("Finder"));
	}
	
}
