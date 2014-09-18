package com.gerenciadorfinanceiro.rest.usuario;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.gerenciadorfinanceiro.rest.ConstantesMapeamentoRest;
import com.gerenciadorfinanceiro.servico.ServicoUsuario;

@Path(ConstantesMapeamentoRest.USUARIO_LOGOUT)
public class UsuarioLogout {
	@GET
	public String logout(@PathParam("login") String login){
		ServicoUsuario.getInstancia().deslogar(login);
		return "OK";
	}
}
