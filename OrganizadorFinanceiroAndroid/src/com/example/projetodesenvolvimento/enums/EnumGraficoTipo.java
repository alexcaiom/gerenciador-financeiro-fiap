/**
 * 
 */
package com.example.projetodesenvolvimento.enums;

/**
 * @author Alex
 *
 */
public enum EnumGraficoTipo {

	GRAFICO_DE_BARRAS_VERTICAL(1, "bvg", "Gráfico de Barras Vertical"),
	GRAFICO_DE_BARRAS_HORIZONTAL(2, "bhg", "Gráfico de Barras Horizontal"),
	GRAFICO_DE_PIZZA(3, "p3", "Gráfico de Pizza");
	
	int codigo;
	String googleCode;
	String nome;
	
	private EnumGraficoTipo(int codigo, String cod_google, String nome) {
		this.codigo = codigo;
		this.googleCode = cod_google;
		this.nome = nome;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getGoogleCode() {
		return googleCode;
	}

	public void setGoogleCode(String googleCode) {
		this.googleCode = googleCode;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public static EnumGraficoTipo getTipoPorCodigoGoogle(String codigoGoogle){
		for (EnumGraficoTipo tipo : EnumGraficoTipo.values()) {
			if (tipo.getGoogleCode().equals(codigoGoogle)) {
				return tipo;
			}
		}
		return null;
	}
	
}
