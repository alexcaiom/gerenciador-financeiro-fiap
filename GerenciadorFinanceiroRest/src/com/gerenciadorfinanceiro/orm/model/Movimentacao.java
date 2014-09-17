/**
 * 
 */
package com.gerenciadorfinanceiro.orm.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.gerenciadorfinanceiro.orm.model.enums.TipoMovimento;
import com.gerenciadorfinanceiro.orm.model.usuario.Usuario;

/**
 * @author AlexDell
 *
 */
@Entity
@Table(name="TBL_MOVIMENTACAO")
public class Movimentacao {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long codigo;
	@ManyToOne
	private Usuario usuario;
	private BigDecimal valor;
	@Enumerated(EnumType.STRING)
	private TipoMovimento tipo;
	private String descricao;
	
	public Movimentacao() {}
	public Movimentacao(long codigo, Usuario usuario, BigDecimal valor,
			TipoMovimento tipo) {
		this.codigo = codigo;
		this.usuario = usuario;
		this.valor = valor;
		this.tipo = tipo;
	}
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
	
}
