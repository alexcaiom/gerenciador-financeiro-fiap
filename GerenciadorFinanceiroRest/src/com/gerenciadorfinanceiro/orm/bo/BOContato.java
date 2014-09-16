/**
 * 
 */
package com.gerenciadorfinanceiro.orm.bo;

import java.util.ArrayList;
import java.util.List;

import com.gerenciadorfinanceiro.abstratas.Classe;
import com.gerenciadorfinanceiro.excecoes.SysErr;
import com.gerenciadorfinanceiro.orm.dao.DAOUsuarioContato;
import com.gerenciadorfinanceiro.orm.dao.FinderUsuarioContato;
import com.gerenciadorfinanceiro.orm.model.usuario.UsuarioContato;

/**
 * @author Alex
 *
 */
public class BOContato extends Classe  {

	private static BOContato instancia;
	private DAOUsuarioContato dao;
	
	private BOContato(){}
	
	public List<UsuarioContato> listarContatos(){
		log("Listando "+getNomeEntidade()+"s");
		List<UsuarioContato> contatos = new ArrayList<UsuarioContato>();
		
		contatos = FinderUsuarioContato.getInstancia().listar();
		
		return contatos;
	}
	
	public static BOContato getInstancia() {
		if(instancia == null){
			instancia = new BOContato();
		}
		return instancia;
	}

	private String getNomeEntidade(){
		return CLASSE_NOME.substring(2);
	}

	/**
	 * Este método tem a inteligencia de verificar se o
	 * Contato a ser persistido já existe. Caso positivo,
	 * o contato é apenas atualizado 
	 */
	public List<UsuarioContato> gravar(List<UsuarioContato> contatos) throws SysErr {
		for (UsuarioContato o : contatos) {
			if(o.getId() !=null && new FinderUsuarioContato().find(o.getId()) != null){
				getDao().atualizar(o);
				log(getNomeEntidade()+" salvo(a) com sucesso!");
			} else {
				getDao().inserir(o);
			}	
		}
		return contatos;
	}


	public void excluir(UsuarioContato o) throws SysErr {
		getDao().excluir(o);
	}

	/**
	 * @return the dao
	 */
	public final DAOUsuarioContato getDao() {
		if(this.dao == null){
			setDao(DAOUsuarioContato.getInstancia());
		}
		return dao;
	}

	/**
	 * @param dao the dao to set
	 */
	public final void setDao(DAOUsuarioContato dao) {
		this.dao = dao;
	}
}
