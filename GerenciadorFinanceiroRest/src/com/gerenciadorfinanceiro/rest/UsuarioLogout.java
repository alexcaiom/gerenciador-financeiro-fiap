package com.gerenciadorfinanceiro.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.gerenciadorfinanceiro.servico.ServicoUsuario;

@Path("/usuario/logout/{login}")
public class UsuarioLogout {
	@GET
	public void deslogar(@PathParam("login") String login){
		ServicoUsuario.getInstancia().deslogar(login);
	}
}
