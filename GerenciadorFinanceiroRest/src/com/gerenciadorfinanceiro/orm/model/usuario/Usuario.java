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

import com.gerenciadorfinanceiro.abstratas.Classe;
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
@XmlRootElement
@Entity
@Table(name="tbl_usuario")
@NamedQueries({
	@NamedQuery(name="selecionarUsuarioPorId", query="select c from Usuario c where c.id = :id"),
	@NamedQuery(name="selecionarPorLogin", query="select c from Usuario c where c.login = :login")
})
public class Usuario extends Classe implements Serializable {
	
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
	@Column(name="visualizacao_endereco", nullable=true)
	private EnumUsuarioVisualizacao visualizacaoEndereco ;
	@Column(name="visualizacao_contato", nullable=true)
	private EnumUsuarioVisualizacao visualizacaoContato ;
	
	@Column(name="visualizacao_social", nullable=true)
	private EnumUsuarioVisualizacao visualizacaoSocial ;
	
	@OneToMany
	private List<UsuarioSocial> social;
	@OneToMany
	private List<UsuarioContato> contato;
	@OneToMany
	private List<UsuarioEndereco> endereco;
	
	@Enumerated(EnumType.STRING)
	private Role tipo;

	private Integer contadorSenhaInvalida = 0;
	
	@Enumerated(EnumType.STRING)
	@Column
	private EnumUsuarioAutenticado status = null;
	
	@OneToMany
	@JoinColumn(name="usuario_id", nullable=true)
	private List<Movimentacao> movimentacoes;
	
	@OneToMany
	@JoinColumn(name="usuario_id", nullable=true, unique=true)
	private List<Usuario> amigos = new ArrayList<Usuario>(); 
	
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

	/**
	 * @return the visualizacaoEndereco
	 */
	public final EnumUsuarioVisualizacao getVisualizacaoEndereco() {
		return visualizacaoEndereco;
	}

	/**
	 * @param visualizacaoEndereco the visualizacaoEndereco to set
	 */
	public final void setVisualizacaoEndereco(EnumUsuarioVisualizacao visualizacaoEndereco) {
		this.visualizacaoEndereco = visualizacaoEndereco;
	}

	/**
	 * @return the visualizacaoContato
	 */
	public final EnumUsuarioVisualizacao getVisualizacaoContato() {
		return visualizacaoContato;
	}

	/**
	 * @param visualizacaoContato the visualizacaoContato to set
	 */
	public final void setVisualizacaoContato(EnumUsuarioVisualizacao visualizacaoContato) {
		this.visualizacaoContato = visualizacaoContato;
	}

	/**
	 * @return the visualizacaoSocial
	 */
	public final EnumUsuarioVisualizacao getVisualizacaoSocial() {
		return visualizacaoSocial;
	}

	/**
	 * @param visualizacaoSocial the visualizacaoSocial to set
	 */
	public final void setVisualizacaoSocial(EnumUsuarioVisualizacao visualizacaoSocial) {
		this.visualizacaoSocial = visualizacaoSocial;
	}

	/**
	 * @return the social
	 */
	public final List<UsuarioSocial> getSocial() {
		return social;
	}

	/**
	 * @param social the social to set
	 */
	public final void setSocial(List<UsuarioSocial> social) {
		this.social = social;
	}

	/**
	 * @return the contato
	 */
	public final List<UsuarioContato> getContato() {
		return contato;
	}

	/**
	 * @param contato the contato to set
	 */
	public final void setContato(List<UsuarioContato> contato) {
		this.contato = contato;
	}

	/**
	 * @return the endereco
	 */
	public final List<UsuarioEndereco> getEndereco() {
		return endereco;
	}

	/**
	 * @param endereco the endereco to set
	 */
	public final void setEndereco(List<UsuarioEndereco> endereco) {
		this.endereco = endereco;
	}

	public Role getTipo() {
		return tipo;
	}



	public void setTipo(Role tipo) {
		this.tipo = tipo;
	}

	public boolean isAdmin() {
		return Role.ADMINISTRADOR.equals(tipo);
	}

	public boolean isUser() {
		return Role.CLIENTE.equals(tipo);
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
		if (naoExiste(movimentacoes)) {
			setMovimentacoes(new ArrayList<Movimentacao>());
		}
		return movimentacoes;
	}

	public void setMovimentacoes(List<Movimentacao> movimentacoes) {
		this.movimentacoes = movimentacoes;
	}

	/**
	 * @return the amigos
	 */
	public final List<Usuario> getAmigos() {
		return amigos;
	}



	/**
	 * @param amigos the amigos to set
	 */
	public final void setAmigos(List<Usuario> amigos) {
		this.amigos = amigos;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getLogin() + " - "+getStatus();
	}
	
	
}