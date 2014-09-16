/**
 * 
 */
package com.gerenciadorfinanceiro.orm.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.gerenciadorfinanceiro.orm.interfaces.IObjectFinder;
import com.gerenciadorfinanceiro.orm.model.usuario.UsuarioContato;

/**
 * @author Alex
 *
 */
public class FinderUsuarioContato extends Finder<UsuarioContato> 
									implements IObjectFinder<UsuarioContato> {


	private static FinderUsuarioContato instancia;

	public FinderUsuarioContato(){
		super(UsuarioContato.class);
		log("Instanciando...");
	}

	public List<UsuarioContato> findByUsuario(Long id) {
		log("Consultando "+getNomeEntidade());

		CriteriaBuilder builder = getEm().getCriteriaBuilder();
		CriteriaQuery<UsuarioContato> criteria = builder.createQuery(UsuarioContato.class);
		Root<UsuarioContato> from = criteria.from(UsuarioContato.class);
		Predicate predicate = builder.equal(from.get("usuario_id"), id);
		criteria.where(predicate);
		TypedQuery<UsuarioContato> query = getEm().createQuery(criteria);
		return query.getResultList();
	}

	@Override
	public UsuarioContato find(Long id) {
		log("Consultando "+getNomeEntidade());
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id", id);
		return super.findOneResult(UsuarioContato.SELECIONAR_POR_ID, parametros);
	}

	public static FinderUsuarioContato getInstancia()  {
		if(instancia == null){
			instancia = new FinderUsuarioContato();
		}
		return instancia;
	}

}
