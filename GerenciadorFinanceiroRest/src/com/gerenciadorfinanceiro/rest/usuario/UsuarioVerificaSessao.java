package com.gerenciadorfinanceiro.rest.usuario;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.gerenciadorfinanceiro.rest.ConstantesMapeamentoRest;
import com.gerenciadorfinanceiro.servico.ServicoUsuario;
import com.gerenciadorfinanceiro.utils.Constantes;

@Path(ConstantesMapeamentoRest.USUARIO_VERIFICA_SESSAO)
public class UsuarioVerificaSessao {
	@GET
	@Produces(Constantes.REST_PRODUCES)
	public String usuarioEstaLogado(@PathParam("login") String login, @PathParam("senha") String senha){
		return ServicoUsuario.getInstancia().usuarioEstaLogado(login, senha);
	}
}
