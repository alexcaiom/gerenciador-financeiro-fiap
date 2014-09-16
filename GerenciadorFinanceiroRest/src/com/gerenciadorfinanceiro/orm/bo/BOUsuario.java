/**
 * 
 */
package com.gerenciadorfinanceiro.orm.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.gerenciadorfinanceiro.abstratas.Classe;
import com.gerenciadorfinanceiro.excecoes.Erro;
import com.gerenciadorfinanceiro.excecoes.ErroNegocio;
import com.gerenciadorfinanceiro.excecoes.SysErr;
import com.gerenciadorfinanceiro.orm.dao.DAOUsuario;
import com.gerenciadorfinanceiro.orm.dao.FinderUsuario;
import com.gerenciadorfinanceiro.orm.interfaces.IUsuarioBO;
import com.gerenciadorfinanceiro.orm.model.EnumUsuarioCadastrado;
import com.gerenciadorfinanceiro.orm.model.usuario.Usuario;

/**
 * @author Alex
 *
 */
public class BOUsuario extends Classe
						implements IUsuarioBO, Serializable {

	private static BOUsuario instancia;
	private DAOUsuario dao;
	private FinderUsuario finder;
	
	private BOUsuario(){}
	
	/**
	 * Metodo de Autenticacao de Usuario
	 * @param ip
	 * @param senha
	 * @return
	 */
	public Usuario autentica(String ip, String senha) throws Erro {
		log("Autenticando "+getNomeEntidade());
		Usuario u = new Usuario();
		u.setIp(ip);
		Usuario uAuth = new  Usuario();
		uAuth = getFinder().findByIP(u.getIp());
		
		//Primeiro verificamos se o usuario existe.
		if(uAuth == null){
			log(getNomeEntidade()+ " inexistente...");
			return uAuth;
		} else {
			//Depois verificamos se usuario e senha informado batem com o banco.
			if (!(ip.equals(uAuth.getIp()) && 
					senha.equals(uAuth.getSenha()))){
				log(getNomeEntidade()+ " informado com senha inválida...");
				throw new ErroNegocio(getNomeEntidade()+ " informado com senha inválida...");
			} else {
				log(getNomeEntidade()+ " autenticado com sucesso");
			}
		}
		return uAuth;
	}

	
	
	public List<Usuario> listarUsuarios(){
		log("Listando "+getNomeEntidade()+"s");
		List<Usuario> usuarios = new ArrayList<Usuario>();
		
		usuarios = getFinder().listar();
		
		for (Usuario usuario : usuarios) {
			System.out.println(usuario);
		}
		getFinder().encerrar();
		
		return usuarios;
	}
	
	public List<String> listarUsuariosString(){
		List<String> usuarios = new ArrayList<String>();
		for(Usuario u: listarUsuarios()){
			usuarios.add(u.toString());
		}
		return usuarios;
	}

	@Override
	public Object inserir(Usuario usuario) {
		log("Inserindo "+getNomeEntidade());
		if(getFinder().findByIP(usuario.getIp()) != null){
			return new ErroNegocio("Usuário já existente!");
		} else {
			Usuario uCadastrado = getDAO().inserir(usuario);
			if (uCadastrado == null){
				return EnumUsuarioCadastrado.ERRO_AO_CRIAR_USUARIO;
			} else {
				return uCadastrado;
			}
		}
		
	}

	@Override
	public void alterar(Usuario usuario) throws SysErr {
		log("Alterando "+getNomeEntidade());
		getDAO().atualizar(usuario);		
	}

	@Override
	public void excluir(Usuario usuario) throws SysErr {
		log("Excluindo "+getNomeEntidade());
		getDAO().excluir(usuario);		
	}
	
	public Usuario pesquisar(Long id){
		Usuario usuario = getFinder().find(id);
		System.out.println(usuario);
		getFinder().encerrar();
		
		return usuario;
	}
	
	public Usuario pesquisarPorIP(String ip){
		return getFinder().findByIP(ip);
	}
	
	public List<Usuario> pesquisarPorIPComo(String ip) {
		List<Usuario> usuarios = getFinder().findByIPLike(ip);
		
		for (Usuario usuario : usuarios) {
			System.out.println(usuario);
		}
		getFinder().encerrar();
		
		return usuarios;
	}
	private String getNomeEntidade(){
		return CLASSE_NOME.substring(2);
	}
	
	private DAOUsuario getDAO(){
		if (this.dao == null) {
			this.dao = DAOUsuario.getInstancia();
		}
		return dao;
	}
	
	private FinderUsuario getFinder(){
		if (this.finder == null) {
			this.finder = FinderUsuario.getInstancia();
		}
		return finder;
	}
	

	public static BOUsuario getInstancia() {
		if(instancia == null){
			instancia = new BOUsuario();
		}
		return instancia;
	}


	
}
