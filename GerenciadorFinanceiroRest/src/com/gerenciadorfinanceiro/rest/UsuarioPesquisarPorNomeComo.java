package com.gerenciadorfinanceiro.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.gerenciadorfinanceiro.orm.model.usuario.Usuario;
import com.gerenciadorfinanceiro.servico.ServicoUsuario;

@Path("/usuario/pesquisarPorIPComo/{login}")
public class UsuarioPesquisarPorNomeComo {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Usuario> pesquisarUsuarioPorIP(@PathParam("login") String login){
		return ServicoUsuario.getInstancia().pesquisarUsuariosPorLoginComo(login);
	}
}
