/**
 * 
 */
package com.gerenciadorfinanceiro.orm.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gerenciadorfinanceiro.orm.model.Movimentacao;
import com.gerenciadorfinanceiro.orm.model.usuario.Usuario;

/**
 * @author Alex
 *
 */
public class FinderMovimentacao extends Finder<Movimentacao>{

	private static FinderMovimentacao instancia;

	public FinderMovimentacao() {
		super(Movimentacao.class);
		log("Instanciando...");
	}

	public Movimentacao find(Long codigo) {
		log("Consultando "+getNomeEntidade());
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("codigo", codigo);
		return super.findOneResult(Usuario.SELECIONAR_POR_ID, parametros);
	}

	public List<Movimentacao> findByUsuarioComo(String login) {
		Usuario u = FinderUsuario.getInstancia().findByLogin(login);
	    return u.getMovimentacoes();
	}

	public static FinderMovimentacao getInstancia()  {
		if(instancia == null){
			instancia = new FinderMovimentacao();
		}
		return instancia;
	}

	public void encerrar(){
		commitAndCloseTransaction();
	}
}
