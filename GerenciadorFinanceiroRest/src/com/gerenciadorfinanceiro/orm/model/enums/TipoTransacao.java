package com.gerenciadorfinanceiro.orm.model.enums;

public enum TipoTransacao {

	DEBITO(1, 'D', "Débito"),
	CREDITO(2, 'C', "Crédito");
	
	int tipo;
	char sigla;
	String descricao;
	
	TipoTransacao(int tipo, char sigla, String descricao) {
		this.tipo = tipo;
		this.sigla = sigla;
		this.descricao = descricao;
	}
	
}
