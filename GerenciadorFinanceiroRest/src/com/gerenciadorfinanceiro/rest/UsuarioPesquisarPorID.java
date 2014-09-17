package com.gerenciadorfinanceiro.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.gerenciadorfinanceiro.orm.model.usuario.Usuario;
import com.gerenciadorfinanceiro.servico.ServicoUsuario;

@Path("/usuario/pesquisarPorID/{id}")
public class UsuarioPesquisarPorID {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario pesquisarUsuarioPorID(@PathParam("id") Long id){
		return ServicoUsuario.getInstancia().pesquisarUsuarioPorID(id);
	}
}