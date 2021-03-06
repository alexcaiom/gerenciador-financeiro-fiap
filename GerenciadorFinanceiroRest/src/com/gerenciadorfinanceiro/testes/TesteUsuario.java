package com.gerenciadorfinanceiro.testes;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.gerenciadorfinanceiro.orm.dao.DAOUsuario;
import com.gerenciadorfinanceiro.orm.model.usuario.Usuario;
import com.gerenciadorfinanceiro.servico.ServicoUsuario;

public class TesteUsuario {

	@Test
	public void TesteUsuarioInsercao() {
		Usuario u = new Usuario("alex", "lifesgood");
		DAOUsuario dao = new DAOUsuario();
		u = dao.inserir(u);
		assertNotNull(u);
		fail("Not yet implemented");
	}
	
	@Test
	public void TestUsuarioLista(){
		List<Usuario> usuarios = ServicoUsuario.getInstancia().listarUsuarios();
		assertNotNull(usuarios);
		for (Usuario usuario : usuarios) {
			System.out.println(usuario.toString());
		}
	}
	
	@Test
	public void TestUsuarioPesquisaPorId(){
		Usuario usuarios = ServicoUsuario.getInstancia().pesquisarUsuarioPorID(1l);
		assertNotNull(usuarios);
	}
	
	@Test
	public void TestUsuarioPesquisaPorIp(){
		List<Usuario> usuario = ServicoUsuario.getInstancia().pesquisarUsuariosPorLoginComo("alex");
		assertNotNull(usuario);
	}
	
	

}
