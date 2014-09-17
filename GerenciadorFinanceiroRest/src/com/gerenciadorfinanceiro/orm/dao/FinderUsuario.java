/**
 * 
 */
package com.gerenciadorfinanceiro.orm.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.gerenciadorfinanceiro.orm.model.usuario.Usuario;

/**
 * @author Alex
 *
 */
public class FinderUsuario extends Finder<Usuario>{

	private static FinderUsuario instancia;

	public FinderUsuario() {
		super(Usuario.class);
		log("Instanciando...");
	}

	public Usuario find(Long id) {
		log("Consultando "+getNomeEntidade());
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id", id);
		return super.findOneResult(Usuario.SELECIONAR_POR_ID, parametros);
	}

	public Usuario findByLogin(String login) {
		log("Consultando "+getNomeEntidade());
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("login", login);
		return super.findOneResult(Usuario.SELECIONAR_POR_IP, parametros);
	}	
	
	public List<Usuario> findByLoginComo(String login) {
		CriteriaBuilder cb = getEm().getCriteriaBuilder();
	    CriteriaQuery<Usuario> c = cb.createQuery(Usuario.class);
	    Root<Usuario> usuario = c.from(Usuario.class);
	    c.select(usuario);
	    List<Predicate> criteria = new ArrayList<Predicate>();

	    if (login != null) {
	        ParameterExpression<String> p = cb.parameter(String.class, "login");
	        criteria.add(cb.like(usuario.<String>get("login"), p));
	    }

	    if (criteria.size() == 0) {
	        throw new RuntimeException("no criteria");
	    } else if (criteria.size() == 1) {
	        c.where(criteria.get(0));
	    } else {
	        c.where(cb.and(criteria.toArray(new Predicate[0])));
	    }

	    TypedQuery<Usuario> q = getEm().createQuery(c);

	    if (login != null) {
	        q.setParameter("login", login);
	    }
	    
	    return q.getResultList();
	}
	

	public static FinderUsuario getInstancia()  {
		if(instancia == null){
			instancia = new FinderUsuario();
		}
		return instancia;
	}

	public void encerrar(){
		commitAndCloseTransaction();
	}
}
