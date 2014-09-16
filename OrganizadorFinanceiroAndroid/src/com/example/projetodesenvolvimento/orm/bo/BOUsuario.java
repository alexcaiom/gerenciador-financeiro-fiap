/**
 * 
 */
package com.example.projetodesenvolvimento.orm.bo;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;

import com.example.projetodesenvolvimento.abstratas.Classe;
import com.example.projetodesenvolvimento.excecoes.ErroNegocio;
import com.example.projetodesenvolvimento.excecoes.SysErr;
import com.example.projetodesenvolvimento.interfaces.IUsuarioBO;
import com.example.projetodesenvolvimento.orm.dao.finder.FinderUsuario;
import com.example.projetodesenvolvimento.orm.modelos.Usuario;
import com.example.projetodesenvolvimento.servicos.ServicoUsuario;

/**
 * @author Alex
 *
 */
public class BOUsuario extends Classe implements IUsuarioBO {

	private static BOUsuario instancia;
	private final Context contexto;
	
	public BOUsuario(Context contexto){
		this.contexto = contexto;
	}
	
	/**
	 * Metodo de Autenticacao de Usuario
	 * @param login
	 * @param senha
	 * @return
	 * @throws SysErr 
	 * @throws ErroNegocio 
	 */
	public void autentica(String login, String senha) throws ErroNegocio, SysErr {
		log("Autenticando "+getNomeEntidade());
		ServicoUsuario.logar(login, senha);
	}

	public static BOUsuario getInstancia(Context contexto) {
		if(instancia == null){
			instancia = new BOUsuario(contexto);
		}
		return instancia;
	}

	@Override
	public void inserir(Usuario usuario) throws ErroNegocio, SysErr {
		Log.i(CLASSE_NOME, "Inserindo "+getNomeEntidade());
		ServicoUsuario.cadastrar(usuario);
	}

	@Override
	public void alterar(Usuario usuario) throws SysErr {
		log("Alterando "+getNomeEntidade());
	}

	@Override
	public void excluir(Usuario usuario) throws SysErr {
		log("Excluindo "+getNomeEntidade());
	}
	
	private String getNomeEntidade(){
		return CLASSE_NOME.substring(2);
	}
}
