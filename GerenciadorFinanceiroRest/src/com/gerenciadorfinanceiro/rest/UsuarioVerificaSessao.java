package com.gerenciadorfinanceiro.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.gerenciadorfinanceiro.servico.ServicoUsuario;

@Path("/usuario/verificaSessao/{login}/{senha}")
public class UsuarioVerificaSessao {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String usuarioEstaLogado(@PathParam("login") String login, @PathParam("senha") String senha){
		return ServicoUsuario.getInstancia().usuarioEstaLogado(login, senha);
	}
}
