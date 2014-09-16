package com.gerenciadorfinanceiro.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.gerenciadorfinanceiro.orm.model.usuario.Usuario;
import com.gerenciadorfinanceiro.servico.Servico;

@Path("/usuario/lista")
public class UsuarioLista {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Usuario> listarUsuarios(){
		return Servico.getInstancia().listarUsuarios();
	}
	
}
