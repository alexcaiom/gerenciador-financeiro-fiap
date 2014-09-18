/**
 * 
 */
package com.gerenciadorfinanceiro.orm.model.usuario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import com.gerenciadorfinanceiro.orm.model.EnumUsuarioAutenticado;
import com.gerenciadorfinanceiro.orm.model.EnumUsuarioVisualizacao;
import com.gerenciadorfinanceiro.orm.model.Movimentacao;

/**
 * @author Alex
 * @since 04/12/2013
 */
/**
 * 
 * Classe que cuida de todas as operações 
 * de persistencia relacionadas a Usuario
 *
 */

//Entidade
//Tabela
@XmlRootElement(name="usuario")
@Entity
@Table(name="tbl_usuario")
@NamedQueries({
	@NamedQuery(name="selecionarUsuarioPorId", query="select c from Usuario c where c.id = :id"),
	@NamedQuery(name="selecionarPorLogin", query="select c from Usuario c where c.login = :login")
})
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = -3548328139537131262L;
	
	public static final String SELECIONAR_POR_ID 		= "selecionarUsuarioPorId";
	public static final String SELECIONAR_POR_IP 		= "selecionarPorLogin";
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(nullable=false, name="ID")
	private Long id;
	@Column(unique=true, nullable=false, name="login")
	private String login = "";
	@Column(name="nome")
	private String nome = "";
	@Column(name="sobrenome")
	private String sobrenome = "";
	@Column(nullable = false, name="email")
	private String email = "";
	
	@Temporal(TemporalType.DATE)
	@Column(name="data_nascimento")
	private Date dataNascimento;
	@Column(nullable = false, name="SENHA")
	private String senha = "";
	
	private Integer contadorSenhaInvalida = 0;
	
	@Enumerated(EnumType.STRING)
	@Column
	private EnumUsuarioAutenticado status = null;
	
	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name="usuario_id", nullable=true)
	private List<Movimentacao> movimentacoes;
	
	public Usuario() {}

	/**
	 * @param id
	 * @param nome
	 * @param senha
	 */
	public Usuario(String login, String senha) {
		this.login = login;
		this.senha = senha;
	}
	/**
	 * @param id
	 * @param nome
	 * @param senha
	 */
	public Usuario(Long id, String login, String senha) {
		this.id = id;
		this.login = login;
		this.senha = senha;
	}

	

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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public String getSobrenome() {
		return sobrenome;
	}



	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the senha
	 */
	public final String getSenha() {
		return senha;
	}

	/**
	 * @param senha the senha to set
	 */
	public final void setSenha(String senha) {
		this.senha = senha;
	}

	public Integer getContadorSenhaInvalida() {
		return contadorSenhaInvalida;
	}

	public void setContadorSenhaInvalida(Integer contadorSenhaInvalida) {
		this.contadorSenhaInvalida = contadorSenhaInvalida;
	}

	/**
	 * @return the status
	 */
	public final EnumUsuarioAutenticado getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public final void setStatus(EnumUsuarioAutenticado status) {
		this.status = status;
	}
	
	public void addMovimentacao(Movimentacao movimentacao){
		getMovimentacoes().add(movimentacao);
	}

	public List<Movimentacao> getMovimentacoes() {
		if (movimentacoes == null) {
			setMovimentacoes(new ArrayList<Movimentacao>());
		}
		return movimentacoes;
	}

	public void setMovimentacoes(List<Movimentacao> movimentacoes) {
		this.movimentacoes = movimentacoes;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getLogin() + " - "+getStatus();
	}
	
	
}
