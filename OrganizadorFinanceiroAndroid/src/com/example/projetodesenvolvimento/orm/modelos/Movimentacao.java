/**
 * 
 */
package com.example.projetodesenvolvimento.orm.modelos;

import java.math.BigDecimal;
import java.util.Calendar;

import com.example.projetodesenvolvimento.abstratas.Classe;
import com.example.projetodesenvolvimento.orm.modelos.enums.TipoMovimento;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author Alex
 *
 */
@DatabaseTable(tableName="TBL_MOVIMENTACAO")
public class Movimentacao {

	@DatabaseField(id = true, allowGeneratedIdInsert = true, generatedId = true, canBeNull = false, dataType = DataType.LONG)
	private Long codigo;
	@DatabaseField(canBeNull = false, dataType = DataType.BIG_DECIMAL_NUMERIC)
	private BigDecimal valor;
	@DatabaseField(canBeNull = false, dataType = DataType.ENUM_STRING)
	private TipoMovimento tipo;
	private String descricao;
	private Calendar data;
	
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
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public TipoMovimento getTipo() {
		return tipo;
	}
	public void setTipo(TipoMovimento tipo) {
		this.tipo = tipo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Calendar getData() {
		return data;
	}
	public void setData(Calendar data) {
		this.data = data;
	}
	
}
