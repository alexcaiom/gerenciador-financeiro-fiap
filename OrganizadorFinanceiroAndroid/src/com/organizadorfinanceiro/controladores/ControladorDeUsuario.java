package com.organizadorfinanceiro.controladores;

import android.content.Context;

import com.organizadorfinanceiro.enums.EnumUsuarioAutenticado;
import com.organizadorfinanceiro.excecoes.Erro;
import com.organizadorfinanceiro.facade.FacadeUsuario;
import com.organizadorfinanceiro.orm.modelos.Usuario;

public class ControladorDeUsuario extends ControladorDeVO<Usuario>{
	
	private Context contexto;
	private static ControladorDeUsuario instancia;
	
	
	public void login(String login, String senha) throws Erro {
		FacadeUsuario.getInstancia(contexto).logar(login, senha);
	}
	
	public void logout(String login) throws Erro {
		FacadeUsuario.getInstancia(contexto).deslogar(login);
		
	}
	
	public void cadastrar(String nome, String email, String login, String senha) throws Erro{
		Usuario usuario = new Usuario(login, senha, EnumUsuarioAutenticado.SUCESSO.getCodigo());
		encriptaVO(usuario);
		FacadeUsuario.getInstancia(contexto).cadastrar(usuario);
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
