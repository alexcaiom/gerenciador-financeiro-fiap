package com.gerenciadorfinanceiro.orm.model.usuario;

public enum Role {
	ADMINISTRADOR(1), 
	CLIENTE(2);
	
	int id;
	
	private Role(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

}
