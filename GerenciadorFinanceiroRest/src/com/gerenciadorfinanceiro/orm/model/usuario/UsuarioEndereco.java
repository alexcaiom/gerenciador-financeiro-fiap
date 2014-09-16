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

/**
 * @author Alex
 *
 */
@Entity
@Table(name="TBL_MYIP_USUARIO_ENDERECO")
@NamedQueries({
//	@NamedQuery(name="selecionarEnderecoPorUsuario", query="select c from UsuarioContato c where c.usuario.id = :idUsuario"),
	@NamedQuery(name="selecionarEnderecoPorId", query="select c from UsuarioContato c where c.id = :id")
})
public class UsuarioEndereco extends Classe implements Serializable {
	private static final long serialVersionUID = 8949702666042993112L;

	public static final String SELECIONAR_POR_USUARIO 	= "selecionarEnderecoPorUsuario";
	public static final String SELECIONAR_POR_ID 		= "selecionarEnderecoPorId";
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="cep")
	private Integer cep;
	
	@Column(name="numero")
	private Integer numero;
	
	@Column(name="complemento")
	private String complemento;

	/*@Transient
	public static IFetchEager<UsuarioEndereco> FETCH_ALL = new IFetchEager<UsuarioEndereco>(){
		public void fetch(UsuarioEndereco orm) {
			FETCH_USUARIO.fetch(orm);
		}
	};*/
	
	/*@Transient
	public static IFetchEager<UsuarioEndereco> FETCH_USUARIO = new IFetchEager<UsuarioEndereco>(){
		public void fetch(UsuarioEndereco orm) {
			if(orm.getUsuario() != null){
				orm.getUsuario().getSenha();
			}
		}
	};*/
	
	/**
	 * @return the id
	 */
	public final Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public final void setId(Integer id) {
		this.id = id;
	}

	public Integer getCep() {
		return cep;
	}

	public void setCep(Integer cep) {
		this.cep = cep;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
}