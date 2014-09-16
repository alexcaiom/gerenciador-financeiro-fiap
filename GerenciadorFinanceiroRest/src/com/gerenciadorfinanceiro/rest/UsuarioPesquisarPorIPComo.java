package com.gerenciadorfinanceiro.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.gerenciadorfinanceiro.orm.model.usuario.Usuario;
import com.gerenciadorfinanceiro.servico.Servico;

@Path("/usuario/pesquisarPorIPComo/{ip}")
public class UsuarioPesquisarPorIPComo {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Usuario> pesquisarUsuarioPorIP(@PathParam("ip") String ip){
		return Servico.getInstancia().pesquisarUsuariosPorIPComo(ip);
	}
}
