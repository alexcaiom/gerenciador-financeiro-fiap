/**
 * 
 */
package com.organizadorfinanceiro.orm.bo;

import android.content.Context;
import android.util.Log;

import com.organizadorfinanceiro.abstratas.Classe;
import com.organizadorfinanceiro.excecoes.Erro;
import com.organizadorfinanceiro.excecoes.ErroNegocio;
import com.organizadorfinanceiro.excecoes.SysErr;
import com.organizadorfinanceiro.interfaces.IUsuarioBO;
import com.organizadorfinanceiro.orm.modelos.Usuario;
import com.organizadorfinanceiro.servicos.remotos.ServicoUsuarioRemoto;

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
	public void autentica(String login, String senha) throws Erro {
		log("Autenticando "+getNomeEntidade());
		ServicoUsuarioRemoto.getInstancia(contexto).logar(login, senha);
	}

	public static BOUsuario getInstancia(Context contexto) {
		if(instancia == null){
			instancia = new BOUsuario(contexto);
		}
		return instancia;
	}

	@Override
	public void inserir(Usuario usuario) throws Erro {
		Log.i(CLASSE_NOME, "Inserindo "+getNomeEntidade());
		ServicoUsuarioRemoto.getInstancia(contexto).cadastrar(usuario);
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
