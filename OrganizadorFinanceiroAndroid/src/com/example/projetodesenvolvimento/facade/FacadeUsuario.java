/**
 * 
 */
package com.example.projetodesenvolvimento.facade;

import android.content.Context;

import com.example.projetodesenvolvimento.abstratas.Classe;
import com.example.projetodesenvolvimento.excecoes.ErroNegocio;
import com.example.projetodesenvolvimento.excecoes.SysErr;
import com.example.projetodesenvolvimento.servicos.remotos.ServicoUsuarioRemoto;

/**
 * @author Alex
 *
 */
public class FacadeUsuario extends Classe {

	private static FacadeUsuario instancia;
	
	public static void logar(String login, String senha) throws ErroNegocio, SysErr{
		ServicoUsuarioRemoto.logar(login, senha);
	}
	
	public static FacadeUsuario getInstancia(Context contexto){
		if (naoExiste(instancia)) {
			instancia = new FacadeUsuario();
		}
		return instancia;
	}
	
}
