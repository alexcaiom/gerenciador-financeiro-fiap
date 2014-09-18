package com.gerenciadorfinanceiro.servico;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.gerenciadorfinanceiro.abstratas.Classe;
import com.gerenciadorfinanceiro.excecoes.Erro;
import com.gerenciadorfinanceiro.excecoes.ErroNegocio;
import com.gerenciadorfinanceiro.excecoes.ErroVO;
import com.gerenciadorfinanceiro.excecoes.SysErr;
import com.gerenciadorfinanceiro.orm.bo.BOUsuario;
import com.gerenciadorfinanceiro.orm.model.usuario.Usuario;
import com.gerenciadorfinanceiro.utils.Sessoes;

public class ServicoUsuario extends Classe {

	private static ServicoUsuario instancia;
	
	public Object logar(String login, String senha){
		try {
			Object o = getUsuarioBO().autentica(login, senha);
			if (o instanceof Usuario) {
				Sessoes.addSessao((Usuario)o);
			}
			return o;
		} catch (Erro e) {
			ErroVO vo = new ErroVO(e);
			return vo;
		}
	}
	
	public List<Usuario> listarUsuarios(){
		return getUsuarioBO().listarUsuarios();
	}
	
	public Usuario pesquisarUsuarioPorID(Long id){
		return getUsuarioBO().pesquisar(id);
	}
	
	public Usuario pesquisarUsuarioPorIP(String ip){
		return getUsuarioBO().pesquisarPorLogin(ip);
	}
	
	public List<Usuario> pesquisarUsuariosPorLoginComo(String ip){
		return getUsuarioBO().pesquisarPorLoginComo(ip);
	}
	
	public Usuario inserirUsuario(Usuario o) throws SysErr, ErroNegocio{
		return getUsuarioBO().inserir(o);
	}
	
	public Object atualizar(String login, Usuario o){
		String resposta = "";
		try {
			if (verificaLogin(login)) {
				getUsuarioBO().alterar(o);
				resposta = "Movimentação alterada com sucesso!";
			} else {
				resposta = "Sessão expirada! Logue-se novamente.";
			}
			return resposta;
		} catch (SysErr e) {
			return new ErroVO(e);
		}
	}

	public Object cadastrar(String login, String email, String senha) {
		Usuario usuario = new Usuario(login, senha);
		usuario.setEmail(email);
		usuario.setStatus(null);
		try {
			usuario = getUsuarioBO().inserir(usuario);
		} catch (Erro e) {
			ErroVO vo = new ErroVO();
			try {
				BeanUtils.copyProperties(vo, e);
			} catch (IllegalAccessException e1) {
				e1.printStackTrace();
			} catch (InvocationTargetException e1) {
				e1.printStackTrace();
			}
			return vo;
		}
		return usuario;
	}

	public String usuarioEstaLogado(String login, String senha) {
		Boolean usuarioLogado = false;
		try{
			usuarioLogado = getUsuarioBO().usuarioEstaLogado(login, senha);
			return usuarioLogado.toString();
		} catch (Erro e) {
			ErroVO vo = new ErroVO();
			try {
				BeanUtils.copyProperties(vo, e);
			} catch (IllegalAccessException e1) {
				e1.printStackTrace();
			} catch (InvocationTargetException e1) {
				e1.printStackTrace();
			}
			return vo.erro;
		}
	}
	
	public void deslogar(String login) {
		getUsuarioBO().deslogar(login);
	}
	

	private BOUsuario getUsuarioBO(){
		return BOUsuario.getInstancia();
	}
	

	private boolean verificaLogin(String login){
		return Sessoes.temSessao(login);
	}
	
	public static ServicoUsuario getInstancia() {
		if (instancia == null) {
			instancia = new ServicoUsuario();
		}
		return instancia;
	}

}