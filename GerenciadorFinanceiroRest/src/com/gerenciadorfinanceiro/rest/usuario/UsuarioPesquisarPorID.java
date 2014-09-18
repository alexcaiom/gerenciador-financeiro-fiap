package com.gerenciadorfinanceiro.rest.usuario;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.gerenciadorfinanceiro.orm.model.usuario.Usuario;
import com.gerenciadorfinanceiro.rest.ConstantesMapeamentoRest;
import com.gerenciadorfinanceiro.servico.ServicoUsuario;
import com.gerenciadorfinanceiro.utils.Constantes;

@Path(ConstantesMapeamentoRest.USUARIO_PESQUISAR_POR_ID)
public class UsuarioPesquisarPorID {
	@GET
	@Produces(Constantes.REST_PRODUCES)
	public Usuario pesquisarUsuarioPorID(@PathParam("id") Long id){
		return ServicoUsuario.getInstancia().pesquisarUsuarioPorID(id);
	}
}
