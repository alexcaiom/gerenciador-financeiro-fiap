/**
 * 
 */
package com.gerenciadorfinanceiro.orm.model.usuario;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.gerenciadorfinanceiro.abstratas.Classe;
import com.gerenciadorfinanceiro.orm.model.TipoContato;

/**
 * @author Alex
 *
 */
@Entity
@Table(name="TBL_MYIP_USUARIO_CONTATO")
@NamedQueries({
//	@NamedQuery(name="selecionarContatoPorUsuario", query="select c from UsuarioContato c where c.usuario.id = :idUsuario"),
	@NamedQuery(name="selecionarContatoPorId", query="select c from UsuarioContato c where c.id = :id")
})
public class UsuarioContato extends Classe implements Serializable {
	private static final long serialVersionUID = 5850604544872797664L;
	
	public static final String SELECIONAR_POR_USUARIO 	= "selecionarContatoPorUsuario";
	public static final String SELECIONAR_POR_ID 		= "selecionarContatoPorId";

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private Long id;
	
	@Column(name="tipo")
	private TipoContato tipo;
	
	@Column(name = "contato")
	private String contato;

	
	/**
	 * @return the id
	 */
	public final Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public final void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the tipo
	 */
	public final TipoContato getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public final void setTipo(TipoContato tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the contato
	 */
	public final String getContato() {
		return contato;
	}

	/**
	 * @param contato the contato to set
	 */
	public final void setContato(String contato) {
		this.contato = contato;
	}

	@Override
	public String toString() {
		return "UsuarioContato [id=" + id + ", tipo=" + tipo + ", contato="
				+ contato +"]";
	}

}