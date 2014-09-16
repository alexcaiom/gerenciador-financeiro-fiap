package com.gerenciadorfinanceiro.servico;

import java.util.List;

import com.gerenciadorfinanceiro.excecoes.SysErr;
import com.gerenciadorfinanceiro.orm.bo.BOUsuario;
import com.gerenciadorfinanceiro.orm.model.usuario.Usuario;

public class Servico {

	private static Servico instancia;
	
	public List<Usuario> listarUsuarios(){
		return getUsuarioBO().listarUsuarios();
	}
	
	public Usuario pesquisarUsuarioPorID(Long id){
		return getUsuarioBO().pesquisar(id);
	}
	
	public Usuario pesquisarUsuarioPorIP(String ip){
		return getUsuarioBO().pesquisarPorIP(ip);
	}
	
	public List<Usuario> pesquisarUsuariosPorIPComo(String ip){
		return getUsuarioBO().pesquisarPorIPComo(ip);
	}
	
	public Object inserirUsuario(Usuario o){
		return getUsuarioBO().inserir(o);
	}
	
	public Object alterarUsuario(Usuario o){
		try {
			getUsuarioBO().alterar(o);
			return new Boolean(true);
		} catch (SysErr e) {
			return e;
		}
	}

	private BOUsuario getUsuarioBO(){
		return BOUsuario.getInstancia();
	}
	
	public static Servico getInstancia() {
		if (instancia == null) {
			instancia = new Servico();
		}
		return instancia;
	}
}
