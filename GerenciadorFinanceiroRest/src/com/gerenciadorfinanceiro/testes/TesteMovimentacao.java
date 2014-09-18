package com.gerenciadorfinanceiro.testes;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;

import com.gerenciadorfinanceiro.orm.bo.BOUsuario;
import com.gerenciadorfinanceiro.orm.model.Movimentacao;
import com.gerenciadorfinanceiro.orm.model.enums.TipoMovimento;
import com.gerenciadorfinanceiro.orm.model.usuario.Usuario;
import com.gerenciadorfinanceiro.servico.ServicoUsuario;

public class TesteMovimentacao {

	@Test
	public void TesteMovimentacaoInsercao() {
		Movimentacao m = new Movimentacao();
		m.setTipo(TipoMovimento.CREDITO);
		m.setValor(new BigDecimal(100.00));
		m.setDescricao("Depósito João");
		
		String login = "alex";
		Usuario usuario =  BOUsuario.getInstancia().pesquisarPorLogin(login);
		
		assertNotNull(usuario);
		usuario.addMovimentacao(m);
		BOUsuario.getInstancia().alterar(usuario);
		assertNotNull(m);
		fail("Not yet implemented");
	}
	
	@Test
	public void TestMovimentacaoLista(){
		List<Usuario> usuarios = ServicoUsuario.getInstancia().listarUsuarios();
		assertNotNull(usuarios);
		for (Usuario usuario : usuarios) {
			System.out.println(usuario.toString());
		}
	}
	
	@Test
	public void TestMovimentacaoPesquisaPorId(){
		Usuario usuarios = ServicoUsuario.getInstancia().pesquisarUsuarioPorID(1l);
		assertNotNull(usuarios);
	}
	
	@Test
	public void TestMovimentacaoPesquisaPorUsuario(){
		List<Usuario> usuario = ServicoUsuario.getInstancia().pesquisarUsuariosPorLoginComo("alex");
		assertNotNull(usuario);
	}
	
}
