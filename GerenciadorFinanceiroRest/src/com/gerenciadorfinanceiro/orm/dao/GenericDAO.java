package com.gerenciadorfinanceiro.orm.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.swing.JOptionPane;

import com.gerenciadorfinanceiro.abstratas.Classe;
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;

abstract class GenericDAO<T> extends Classe implements Serializable {
	private static final long serialVersionUID = 1L;

	private static EntityManagerFactory emf;
	private EntityManager em;

	private Class<T> entityClass;

	public void beginTransaction() {
		if(!getEm().getTransaction().isActive()){
			getEm().getTransaction().begin();
			System.out.println("Transação inicializada para "+entityClass.getSimpleName());
		} else {
			System.err.println("Transação já inicializada para "+entityClass.getSimpleName());
		}
	}

	public void commit() {
		getEm().getTransaction().commit();
		System.out.println("Transação efetuada para "+entityClass.getSimpleName());
	}

	public void rollback() {
		getEm().getTransaction().rollback();
		System.err.println("Transação revertida para "+entityClass.getSimpleName());
	}

	public void closeTransaction() {
		if(em != null && em.isOpen()){
			em.close();
			em = null;
			System.out.println("EntityManager fechado para "+entityClass.getSimpleName());
		}
	}

	public void commitAndCloseTransaction() {
		commit();
		closeTransaction();
	}

	public void flush() {
		getEm().flush();
	}

	public void joinTransaction() {
		getEm().joinTransaction();
	}

	public GenericDAO(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public T inserir(T entity) {
		try{
			beginTransaction();
			getEm().persist(entity);
			commitAndCloseTransaction();
		}catch (Exception e){
			if(e instanceof MySQLSyntaxErrorException){
				avisarBDNaoExistente();
			} else {
				e.printStackTrace();
			}
		}
		return entity;
	}

	public void excluir(T entityToBeRemoved) {
		try{
			beginTransaction();		 
			getEm().remove(entityToBeRemoved);
			commitAndCloseTransaction();
		}catch (Exception e){
			if(e instanceof MySQLSyntaxErrorException){
				avisarBDNaoExistente();
			} else {
				e.printStackTrace();
			}
		}
	}

	public T atualizar(T entity) {
		beginTransaction();
		entity = getEm().merge(entity);
		commitAndCloseTransaction();
		return entity;
	}

	public T pesquisar(Long entityID) {
		return getEm().find(entityClass, entityID);
	}

	public T findReferenceOnly(Long entityID) {
		return getEm().getReference(entityClass, entityID);
	}

	// Using the unchecked because JPA does not have a
	// em.getCriteriaBuilder().createQuery()<T> method
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<T> listar() {
		beginTransaction();
		CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		List resultList = em.createQuery(cq).getResultList();
		return resultList;
	}

	// Using the unchecked because JPA does not have a
	// query.getSingleResult()<T> method
	@SuppressWarnings("unchecked")
	protected T findOneResult(String namedQuery, Map<String, Object> parameters) {
		T result = null;

		try {
			Query query = getEm().createNamedQuery(namedQuery);

			// Method that will populate parameters if they are passed not null and empty
			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(query, parameters);
			}

			result = (T) query.getSingleResult();

		} catch (NoResultException e) {
			System.out.println("No result found for named query: " + namedQuery);
		} catch (Exception e) {
			System.out.println("Error while running query: " + e.getMessage());
			e.printStackTrace();
		}

		return result;
	}

	private void populateQueryParameters(Query query, Map<String, Object> parameters) {
		for (Entry<String, Object> entry : parameters.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
	}

	private static EntityManagerFactory getEmf()  {
		if(emf == null){
			setEmf(Persistence.createEntityManagerFactory("MyIPPU"));
		}
		return emf;
	}

	private static void setEmf(EntityManagerFactory emf) {
		GenericDAO.emf = emf;
	}
	
	private void avisarBDNaoExistente() {
		JOptionPane.showMessageDialog(null, "Crie o Banco de Dados informado no Persistence.xml", "Banco de Dados inexistente", JOptionPane.ERROR_MESSAGE);		
	}

	public EntityManager getEm() {
		if(em == null || !em.isOpen()){
			em = getEmf().createEntityManager();
		}
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
}
