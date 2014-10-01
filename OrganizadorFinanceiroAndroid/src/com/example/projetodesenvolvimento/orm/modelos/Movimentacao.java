/**
 * 
 */
package com.example.projetodesenvolvimento.orm.modelos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import com.example.projetodesenvolvimento.orm.modelos.enums.TipoMovimento;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author Alex
 *
 */
@DatabaseTable(tableName="TBL_MOVIMENTACAO")
public class Movimentacao implements Serializable {

	@DatabaseField(id = true, allowGeneratedIdInsert = true, generatedId = true, canBeNull = false, dataType = DataType.LONG)
	private Long codigo;
	@DatabaseField(canBeNull = false, dataType = DataType.BIG_DECIMAL_NUMERIC)
	private BigDecimal valor;
	@DatabaseField(canBeNull = false, dataType = DataType.ENUM_STRING)
	private TipoMovimento tipo;
	private String descricao;
	@DatabaseField(columnName="data", dataType = DataType.DATE_LONG)
	private Calendar data;
	private String login;
	@DatabaseField(columnName="fonte", dataType = DataType.STRING)
	private Categoria categoria;
	
	public Movimentacao() {}
	public Movimentacao(long codigo, BigDecimal valor,
			TipoMovimento tipo) {
		this.codigo = codigo;
		this.valor = valor;
		this.tipo = tipo;
	}
	
	public Long getCodigo() {
		return codigo;
	}
	public Movimentacao comCodigo(long codigo) {
		this.codigo = codigo;
		return this;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public Movimentacao comValor(BigDecimal valor) {
		this.valor = valor;
		return this;
	}
	public TipoMovimento getTipo() {
		return tipo;
	}
	public Movimentacao comTipo(TipoMovimento tipo) {
		this.tipo = tipo;
		return this;
	}
	public String getDescricao() {
		return descricao;
	}
	public Movimentacao comDescricao(String descricao) {
		this.descricao = descricao;
		return this;
	}
	public String getLogin() {
		return login;
	}
	public Movimentacao comLogin(String login) {
		this.login = login;
		return this;
	}
	public Calendar getData() {
		return data;
	}
	public Movimentacao comData(Calendar data) {
		this.data = data;
		return this;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	@Override
	public String toString() {
		return "R$ " + valor + ", tipo=" + tipo.getSigla();
	}
	
}
