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
import com.gerenciadorfinanceiro.orm.model.usuario.UsuarioSocial;

/**
 * @author Alex
 * 
 */
public class FinderUsuarioSocial extends Finder<UsuarioSocial> 
								implements IObjectFinder<UsuarioSocial> {

	private static IObjectFinder<UsuarioSocial> instancia;

	private FinderUsuarioSocial() {
		super(UsuarioSocial.class);
		log("Instanciando...");
	}

	@Override
	public UsuarioSocial find(Long id) {
		log("Consultando " + getNomeEntidade());
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id", id);
		return super.findOneResult(UsuarioSocial.SELECIONAR_POR_ID, parametros);
	}

	public List<UsuarioSocial> findByUsuario(Long id) {
		log("Consultando " + getNomeEntidade() + "(s) por usuario");
		CriteriaBuilder builder = getEm().getCriteriaBuilder();
		CriteriaQuery<UsuarioSocial> criteria = builder.createQuery(UsuarioSocial.class);
		Root<UsuarioSocial> from = criteria.from(UsuarioSocial.class);
		Predicate predicate = builder.equal(from.get("usuario_id"), id);
		criteria.where(predicate);
		TypedQuery<UsuarioSocial> query = getEm().createQuery(criteria);
		return query.getResultList();
	}

	public static IObjectFinder<UsuarioSocial> getInstancia() {
		if (instancia == null) {
			instancia = new FinderUsuarioSocial();
		}
		return instancia;
	}

}
