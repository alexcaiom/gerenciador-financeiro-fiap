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
import com.gerenciadorfinanceiro.orm.model.EnumUsuarioAutenticado;
import com.gerenciadorfinanceiro.orm.model.EnumUsuarioCadastrado;
import com.gerenciadorfinanceiro.orm.model.usuario.Usuario;
import com.gerenciadorfinanceiro.utils.Sessoes;
import com.gerenciadorfinanceiro.utils.UtilsData;

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
	 * @param login
	 * @param senha
	 * @return
	 */
	public Usuario autentica(String login, String senha) throws Erro {
		log("Autenticando "+getNomeEntidade());
		Usuario u = new Usuario();
		u.setLogin(login);
		Usuario usuarioRecuperadoDoBD = new  Usuario();
		usuarioRecuperadoDoBD = getFinder().findByLogin(u.getLogin());
		
		//Primeiro verificamos se o usuario existe.
		if(existe(usuarioRecuperadoDoBD)){
			//Verificamos se usuario e senha informado batem com o banco.
			boolean usuarioConfere = (login.equals(usuarioRecuperadoDoBD.getLogin()) && senha.equals(usuarioRecuperadoDoBD.getSenha()));
			boolean usuarioEstaBloqueado = !(usuarioRecuperadoDoBD.getStatus() == null || !usuarioRecuperadoDoBD.getStatus().equals(EnumUsuarioAutenticado.USUARIO_BLOQUEADO));
			boolean ehFinalDeSemana = UtilsData.ehFinalDeSemana();
			boolean ehHorarioComercial = UtilsData.ehHorarioComercial();
			
			boolean usuarioPodeAcessar = usuarioConfere 
								&& !usuarioEstaBloqueado 
								&& !ehFinalDeSemana 
								&& ehHorarioComercial; 
			
			if (usuarioPodeAcessar){
				log(getNomeEntidade()+ " autenticado com sucesso");
			} else if (!usuarioConfere) {
				log(getNomeEntidade()+ " informado com senha inválida...");
				usuarioRecuperadoDoBD.setContadorSenhaInvalida(usuarioRecuperadoDoBD.getContadorSenhaInvalida()+1);
				boolean avisaUsuarioBloqueioNoProximoErro = (3 - usuarioRecuperadoDoBD.getContadorSenhaInvalida()) == 1;
				trataSenhaInvalidaDoUsuario(usuarioRecuperadoDoBD);
				
				if (avisaUsuarioBloqueioNoProximoErro) {
					throw new ErroNegocio(EnumUsuarioAutenticado.SENHA_INVALIDA_ULTIMA_TENTATIVA.getMensagem());
				} else {
					throw new ErroNegocio(EnumUsuarioAutenticado.SENHA_INVALIDA.getMensagem());
				}
			} else if(usuarioEstaBloqueado) {
				throw new ErroNegocio(EnumUsuarioAutenticado.USUARIO_BLOQUEADO.getMensagem() );
			} else if (ehFinalDeSemana) {
				throw new ErroNegocio(EnumUsuarioAutenticado.USUARIO_BLOQUEADO.getMensagem() );
			}
		} else {
			log(EnumUsuarioAutenticado.USUARIO_INEXISTENTE.getMensagem());
			throw new ErroNegocio(EnumUsuarioAutenticado.USUARIO_INEXISTENTE.getMensagem());
		}
		return usuarioRecuperadoDoBD;
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
	public Usuario inserir(Usuario usuario) throws SysErr, ErroNegocio {
		log("Inserindo "+getNomeEntidade());
		boolean usuarioJaExiste = getFinder().findByLogin(usuario.getLogin()) != null;
		if(usuarioJaExiste){
			throw new ErroNegocio(EnumUsuarioCadastrado.USUARIO_DUPLICADO.getMensagem());
		} else {
			Usuario uCadastrado = getDAO().inserir(usuario);
			boolean ocorreuAlgumErro = uCadastrado == null;
			if (ocorreuAlgumErro){
				throw new ErroNegocio(EnumUsuarioCadastrado.ERRO_AO_CRIAR_USUARIO.getMensagem());
			} else {
				Sessoes.addSessao(uCadastrado);
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
	
	public Usuario pesquisarPorLogin(String login){
		return getFinder().findByLogin(login);
	}
	
	public List<Usuario> pesquisarPorLoginComo(String login) {
		List<Usuario> usuarios = getFinder().findByLoginComo(login);
		
		for (Usuario usuario : usuarios) {
			System.out.println(usuario);
		}
		getFinder().encerrar();
		
		return usuarios;
	}
	
	public Boolean usuarioEstaLogado(String login, String senha)  {
		Object o = null;
		try{
			o = autentica(login, senha);
		} catch (ErroNegocio e){
			if (e.getErro().equals(EnumUsuarioAutenticado.USUARIO_INEXISTENTE.getMensagem())) {
				return false;
			}
		}
		Usuario u = null;
		if (existe(o) && o instanceof Usuario) {
			u = (Usuario) o;
		}
		boolean usuarioEstaLogado = false;
		if (existe(u)) {
			usuarioEstaLogado = Sessoes.usuarioLogado(u.getLogin());
		}
		return usuarioEstaLogado;
	}

	public void deslogar(String login) {
		Sessoes.expirarSessao(login);
	}
	
	private void trataSenhaInvalidaDoUsuario(Usuario usuarioRecuperadoDoBD) {
		int numeroDeChancesRestantes = 3 - usuarioRecuperadoDoBD.getContadorSenhaInvalida();
		boolean deveBloquearUsuario = numeroDeChancesRestantes == 0;
		if (deveBloquearUsuario) {
			usuarioRecuperadoDoBD.setStatus(EnumUsuarioAutenticado.USUARIO_BLOQUEADO);				
		}
		getDAO().atualizar(usuarioRecuperadoDoBD);
	}
	
	private String getNomeEntidade(){
		return CLASSE_NOME.substring(2);
	}
	
	private DAOUsuario getDAO(){
		if (naoExiste(this.dao)) {
			this.dao = DAOUsuario.getInstancia();
		}
		return dao;
	}
	
	private FinderUsuario getFinder(){
		if (naoExiste(this.finder)) {
			this.finder = FinderUsuario.getInstancia();
		}
		return finder;
	}
	
	public static BOUsuario getInstancia() {
		if(naoExiste(instancia)){
			instancia = new BOUsuario();
		}
		return instancia;
	}
}
