package com.example.projetodesenvolvimento.controladores;

import android.content.Context;

import com.example.projetodesenvolvimento.enums.EnumUsuarioAutenticado;
import com.example.projetodesenvolvimento.excecoes.ErroNegocio;
import com.example.projetodesenvolvimento.orm.modelos.Usuario;
import com.example.projetodesenvolvimento.ws.implementacoes.UsuarioWS;

public class ControladorDeUsuario extends ControladorDeVO<Usuario>{
	
	private Context contexto;
	private static ControladorDeUsuario instancia;
	
	public void cadastrar(String nome, String email, String login, String senha, String confirmacaoSenha) throws ErroNegocio{
		Usuario usuario = new Usuario(login, confirmacaoSenha, EnumUsuarioAutenticado.SUCESSO.getCodigo());
		encriptaVO(usuario);
		UsuarioWS.getInstancia(contexto).cadastrar(usuario);
	}
	
	public Context getContexto() {
		return contexto;
	}

	public void setContexto(Context contexto) {
		this.contexto = contexto;
	}

	public static ControladorDeUsuario getInstancia(Context contexto){
		if (naoExiste(instancia)) {
			instancia = new ControladorDeUsuario();
			instancia.setContexto(contexto);
		}
		return instancia;
	}

	@Override
	void encriptaVO(Usuario o) {
		
		
	}
	
}
