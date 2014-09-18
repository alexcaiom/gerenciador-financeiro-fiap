package com.gerenciadorfinanceiro.rest.usuario;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.gerenciadorfinanceiro.orm.model.usuario.Usuario;
import com.gerenciadorfinanceiro.rest.ConstantesMapeamentoRest;
import com.gerenciadorfinanceiro.servico.ServicoUsuario;
import com.gerenciadorfinanceiro.utils.Constantes;

@Path(ConstantesMapeamentoRest.USUARIO_PESQUISAR_POR_LOGIN_COMO)
public class UsuarioPesquisarPorLoginComo {
	@GET
	@Produces(Constantes.REST_PRODUCES)
	public List<Usuario> pesquisarUsuarioPorIP(@PathParam("login") String login){
		return ServicoUsuario.getInstancia().pesquisarUsuariosPorLoginComo(login);
	}
}
