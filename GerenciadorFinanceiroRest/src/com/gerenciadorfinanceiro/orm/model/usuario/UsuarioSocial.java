/**
 * 
 */
package com.gerenciadorfinanceiro.orm.model.usuario;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.gerenciadorfinanceiro.abstratas.Classe;
import com.gerenciadorfinanceiro.orm.model.RedeSocial;

/**
 * @author Alex
 *
 */
@Entity
@Table(name="TBL_MYIP_USUARIO_SOCIAL")
@NamedQueries({
//			@NamedQuery(name="selecionarSocialPorUsuario", query="select s from UsuarioSocial s where s.usuario.id = :idUsuario"),
			@NamedQuery(name="selecionarSocialPorId", query="select s from UsuarioSocial s where s.id = :id")
		})
public class UsuarioSocial extends Classe implements Serializable {
	private static final long serialVersionUID = -8265143420654427059L;
	public static final String SELECIONAR_POR_USUARIO 	= "selecionarSocialPorUsuario";
	public static final String SELECIONAR_POR_ID 		= "selecionarSocialPorId";

	@Id
	@Column
	private Long id;

	@Column
	private RedeSocial tipo;
	
	@Column
	private String usuarioSocial;
	
	@Column
	private String senhaSocial;
	

	/**
	 * @return the id
	 */
	public final Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public final void setId(Long id) {
		this.id = id;
	}

	public RedeSocial getTipo() {
		return tipo;
	}

	public void setTipo(RedeSocial tipo) {
		this.tipo = tipo;
	}

	public String getUsuarioSocial() {
		return usuarioSocial;
	}

	public void setUsuarioSocial(String usuarioSocial) {
		this.usuarioSocial = usuarioSocial;
	}

	public String getSenhaSocial() {
		return senhaSocial;
	}

	public void setSenhaSocial(String senhaSocial) {
		this.senhaSocial = senhaSocial;
	}

	@Override
	public String toString() {
		return "UsuarioSocial [id=" + id + ", tipo="
				+ tipo + ", usuarioSocial=" + usuarioSocial + ", senhaSocial="
				+ senhaSocial + "]";
	}

}
