package com.organizadorfinanceiro.orm.modelos.enums;
public enum TipoMovimento {

	DEBITO(1, "D", "Debito"),
	CREDITO(2, "C", "Credito"),
	PRESENTE(3, "P", "Presente");
	
	int tipo;
	String sigla;
	String descricao;
	
	TipoMovimento(int tipo, String sigla, String descricao) {
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

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public static TipoMovimento getMovimentoPorTipo(int tipo){
		for (TipoMovimento tipoDoMovimento : TipoMovimento.values()) {
			if (tipoDoMovimento.getTipo() == tipo) {
				return tipoDoMovimento;
			}
		}
		return TipoMovimento.DEBITO;
	}
	
}