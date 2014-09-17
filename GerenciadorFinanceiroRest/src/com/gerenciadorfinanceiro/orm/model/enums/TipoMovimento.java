package com.gerenciadorfinanceiro.orm.model.enums;

public enum TipoMovimento {

	DEBITO(1, 'D', "Debito"),
	CREDITO(2, 'C', "Credito");
	
	int tipo;
	char sigla;
	String descricao;
	
	TipoMovimento(int tipo, char sigla, String descricao) {
		this.tipo = tipo;
		this.sigla = sigla;
		this.descricao = descricao;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public char getSigla() {
		return sigla;
	}

	public void setSigla(char sigla) {
		this.sigla = sigla;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public static TipoMovimento getMovimentoPorTipo(char tipo){
		for (TipoMovimento tipoDoMovimento : TipoMovimento.values()) {
			if (tipoDoMovimento.getSigla() == tipo) {
				return tipoDoMovimento;
			}
		}
		return null;
	}
	
}
