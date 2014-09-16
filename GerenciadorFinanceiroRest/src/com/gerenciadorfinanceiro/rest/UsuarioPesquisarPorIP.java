package com.gerenciadorfinanceiro.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.gerenciadorfinanceiro.orm.model.usuario.Usuario;
import com.gerenciadorfinanceiro.servico.Servico;

@Path("/usuario/pesquisarPorIP/{ip}")
public class UsuarioPesquisarPorIP {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario pesquisarUsuarioPorIP(@PathParam("ip") String ip){
		return Servico.getInstancia().pesquisarUsuarioPorIP(ip);
	}
}
