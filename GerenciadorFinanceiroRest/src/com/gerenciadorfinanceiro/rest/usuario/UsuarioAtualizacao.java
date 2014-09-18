package com.gerenciadorfinanceiro.rest.usuario;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.gerenciadorfinanceiro.orm.model.EnumUsuarioAutenticado;
import com.gerenciadorfinanceiro.orm.model.usuario.Usuario;
import com.gerenciadorfinanceiro.rest.ConstantesMapeamentoRest;
import com.gerenciadorfinanceiro.servico.ServicoUsuario;
import com.gerenciadorfinanceiro.utils.Constantes;

@Path(ConstantesMapeamentoRest.USUARIO_ATUALIZACAO)
public class UsuarioAtualizacao {
	@GET
	@Produces(Constantes.REST_PRODUCES)
	public Object atualizar(@PathParam("login") String login, 									@PathParam("nome") String nome,
						@PathParam("sobrenome") String sobrenome,							@PathParam("email") String email, 
						@PathParam("dataNascimento") String dataNascimento,					@PathParam("senha") String senha,
						@PathParam("contadorSenhaInvalida") Integer contadorSenhaInvalida,	@PathParam("status") Integer status){
		Usuario usuario = new Usuario(login, senha);
		usuario.setContadorSenhaInvalida(contadorSenhaInvalida);
		usuario.setEmail(email);
		usuario.setNome(nome);
		usuario.setSobrenome(sobrenome);
		usuario.setStatus(EnumUsuarioAutenticado.get(status));
		return ServicoUsuario.getInstancia().atualizar(login, usuario);
	}
}
