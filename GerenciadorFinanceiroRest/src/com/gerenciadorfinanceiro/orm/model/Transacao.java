/**
 * 
 */
package com.gerenciadorfinanceiro.orm.model;

import java.math.BigDecimal;

import com.gerenciadorfinanceiro.orm.model.enums.TipoTransacao;
import com.gerenciadorfinanceiro.orm.model.usuario.Usuario;

/**
 * @author AlexDell
 *
 */
public class Transacao {

	private long codigo;
	private Usuario usuario;
	private BigDecimal valor;
	private TipoTransacao tipo;
	
	public Transacao() {}
	public Transacao(long codigo, Usuario usuario, BigDecimal valor,
			TipoTransacao tipo) {
		this.codigo = codigo;
		this.usuario = usuario;
		this.valor = valor;
		this.tipo = tipo;
	}
	public long getCodigo() {
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
	public TipoTransacao getTipo() {
		return tipo;
	}
	public void setTipo(TipoTransacao tipo) {
		this.tipo = tipo;
	}
	
}
