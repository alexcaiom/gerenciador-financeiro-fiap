package com.gerenciadorfinanceiro.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.gerenciadorfinanceiro.servico.ServicoUsuario;

@Path("/usuario/cadastro/{login}/{email}/{senha}")
public class UsuarioCadastro {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Object logar(@PathParam("login") String login, @PathParam("email") String email, @PathParam("senha") String senha){
		return ServicoUsuario.getInstancia().cadastrar(login, email, senha);
	}
}
