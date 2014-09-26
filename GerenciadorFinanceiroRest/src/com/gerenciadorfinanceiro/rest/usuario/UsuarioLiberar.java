package com.gerenciadorfinanceiro.rest.usuario;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.gerenciadorfinanceiro.rest.ConstantesMapeamentoRest;
import com.gerenciadorfinanceiro.servico.ServicoUsuario;
import com.gerenciadorfinanceiro.utils.Constantes;


@Path(ConstantesMapeamentoRest.USUARIO_LIBERAR)
public class UsuarioLiberar {

	@GET
	@Produces(Constantes.REST_PRODUCES)
	public Object atualizar(@PathParam("login") String login){
		return ServicoUsuario.getInstancia().liberarUsuario(login);
	}
	
}
