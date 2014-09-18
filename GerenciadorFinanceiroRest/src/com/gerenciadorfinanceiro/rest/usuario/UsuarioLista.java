package com.gerenciadorfinanceiro.rest.usuario;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.gerenciadorfinanceiro.orm.model.usuario.Usuario;
import com.gerenciadorfinanceiro.rest.ConstantesMapeamentoRest;
import com.gerenciadorfinanceiro.servico.ServicoUsuario;
import com.gerenciadorfinanceiro.utils.Constantes;

@Path(ConstantesMapeamentoRest.USUARIO_LISTA)
public class UsuarioLista {

	@GET
	@Produces(Constantes.REST_PRODUCES)
	public List<Usuario> listarUsuarios(){
		return ServicoUsuario.getInstancia().listarUsuarios();
	}
	
}
