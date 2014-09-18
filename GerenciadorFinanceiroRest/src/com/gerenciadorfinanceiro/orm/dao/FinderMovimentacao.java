/**
 * 
 */
package com.gerenciadorfinanceiro.orm.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.gerenciadorfinanceiro.orm.model.Movimentacao;
import com.gerenciadorfinanceiro.orm.model.enums.TipoMovimento;
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
	
	public List<Movimentacao> find(Movimentacao paramMovimentacao) {
		Long codigo			= null;
		String descricao  	= null;
		TipoMovimento tipo	= null;
		BigDecimal valor 	= null;
		if (existe(paramMovimentacao)) {
			//Primeiro verificamos os campos que podemos usar
			codigo = paramMovimentacao.getCodigo();
			descricao = paramMovimentacao.getDescricao();
			tipo = paramMovimentacao.getTipo();
			valor = paramMovimentacao.getValor();
		}
		
		CriteriaBuilder cb = getEm().getCriteriaBuilder();
	    CriteriaQuery<Movimentacao> c = cb.createQuery(Movimentacao.class);
	    Root<Movimentacao> tabelaDeMovimentacao = c.from(Movimentacao.class);
	    c.select(tabelaDeMovimentacao);
	    List<Predicate> criteria = new ArrayList<Predicate>();

	    if (existe(codigo)) {
	        ParameterExpression<String> p = cb.parameter(String.class, "codigo");
	        criteria.add(cb.equal(tabelaDeMovimentacao.<String>get("codigo"), p));
	    }
	    
	    if (existe(descricao)) {
	    	ParameterExpression<String> p = cb.parameter(String.class, "descricao");
	    	criteria.add(cb.equal(tabelaDeMovimentacao.<String>get("descricao"), p));
	    }
	    
	    if (existe(tipo)) {
	    	ParameterExpression<String> p = cb.parameter(String.class, "tipo");
	    	criteria.add(cb.equal(tabelaDeMovimentacao.<String>get("tipo"), p));
	    }
	    
	    if (existe(valor)) {
	    	ParameterExpression<String> p = cb.parameter(String.class, "valor");
	    	criteria.add(cb.equal(tabelaDeMovimentacao.<String>get("valor"), p));
	    }

	    if (criteria.size() == 0) {
	        throw new RuntimeException("no criteria");
	    } else if (criteria.size() == 1) {
	        c.where(criteria.get(0));
	    } else {
	        c.where(cb.and(criteria.toArray(new Predicate[0])));
	    }

	    TypedQuery<Movimentacao> q = getEm().createQuery(c);

	    if (existe(codigo)) {
	        q.setParameter("codigo", codigo);
	    }
	    
	    if (existe(descricao)) {
	    	q.setParameter("descricao", descricao);
	    }
	    
	    if (existe(tipo)) {
	    	q.setParameter("tipo", tipo);
	    }
	    
	    if (existe(valor)) {
	    	q.setParameter("valor", valor);
	    }
	    
	    return q.getResultList();		
	}
	
	public List<Movimentacao> pesquisarPorFaixaDePreco(BigDecimal valorDe, BigDecimal valorAte) {
		if (naoExiste(valorDe)) {
			//Primeiro verificamos os campos que podemos usar
			return new ArrayList<Movimentacao>();
		}
		if (naoExiste(valorAte)) {
			//Primeiro verificamos os campos que podemos usar
			return new ArrayList<Movimentacao>();
		}
		
		CriteriaBuilder cb = getEm().getCriteriaBuilder();
	    CriteriaQuery<Movimentacao> c = cb.createQuery(Movimentacao.class);
	    Root<Movimentacao> tabelaDeMovimentacao = c.from(Movimentacao.class);
	    c.select(tabelaDeMovimentacao);
	    List<Predicate> criteria = new ArrayList<Predicate>();

	    if (existe(valorDe)) {
	        ParameterExpression<BigDecimal> p = cb.parameter(BigDecimal.class, "valorDe");
	        criteria.add(cb.greaterThan(tabelaDeMovimentacao.<BigDecimal>get("valor"), p));
	    }
	    
	    if (existe(valorAte)) {
	    	ParameterExpression<BigDecimal> p = cb.parameter(BigDecimal.class, "valorAte");
	    	criteria.add(cb.lessThan(tabelaDeMovimentacao.<BigDecimal>get("valor"), p));
	    }
	    
	    if (criteria.size() == 0) {
	        throw new RuntimeException("no criteria");
	    } else if (criteria.size() == 1) {
	        c.where(criteria.get(0));
	    } else {
	        c.where(cb.and(criteria.toArray(new Predicate[0])));
	    }

	    TypedQuery<Movimentacao> q = getEm().createQuery(c);

	    if (existe(valorDe)) {
	        q.setParameter("valorDe", valorDe);
	    }
	    
	    if (existe(valorAte)) {
	    	q.setParameter("valorAte", valorAte);
	    }
	    
	    return q.getResultList();
	}
	
	/*public List<Movimentacao> pesquisarPorFaixaDePreco(String valorDe, String valorAte) {
		if (naoExiste(valorDe)) {
			//Primeiro verificamos os campos que podemos usar
			return new ArrayList<Movimentacao>();
		}
		if (naoExiste(valorAte)) {
			//Primeiro verificamos os campos que podemos usar
			return new ArrayList<Movimentacao>();
		}
		
		CriteriaBuilder cb = getEm().getCriteriaBuilder();
	    CriteriaQuery<Movimentacao> c = cb.createQuery(Movimentacao.class);
	    Root<Movimentacao> tabelaDeMovimentacao = c.from(Movimentacao.class);
	    c.select(tabelaDeMovimentacao);
	    List<Predicate> criteria = new ArrayList<Predicate>();

	    if (existe(valorDe)) {
	        ParameterExpression<String> p = cb.parameter(String.class, "valor");
	        criteria.add(cb.greaterThan(tabelaDeMovimentacao.<String>get("valor"), p));
	    }
	    
	    if (existe(valorAte)) {
	    	ParameterExpression<String> p = cb.parameter(String.class, "valor");
	    	criteria.add(cb.lessThan(tabelaDeMovimentacao.<String>get("valor"), p));
	    }
	    
	    if (criteria.size() == 0) {
	        throw new RuntimeException("no criteria");
	    } else if (criteria.size() == 1) {
	        c.where(criteria.get(0));
	    } else {
	        c.where(cb.and(criteria.toArray(new Predicate[0])));
	    }

	    TypedQuery<Movimentacao> q = getEm().createQuery(c);

	    if (existe(valorDe)) {
	        q.setParameter("valor", valorDe);
	    }
	    
	    if (existe(valorAte)) {
	    	q.setParameter("valor", valorAte);
	    }
	    
	    return q.getResultList();
	}*/
	
	public List<Movimentacao> pesquisarPorPeriodo(Calendar dataDe, Calendar dataAte){
		if (naoExiste(dataDe)) {
			//Primeiro verificamos os campos que podemos usar
			return new ArrayList<Movimentacao>();
		}
		if (naoExiste(dataAte)) {
			//Primeiro verificamos os campos que podemos usar
			return new ArrayList<Movimentacao>();
		}
		
		CriteriaBuilder cb = getEm().getCriteriaBuilder();
	    CriteriaQuery<Movimentacao> c = cb.createQuery(Movimentacao.class);
	    Root<Movimentacao> tabelaDeMovimentacao = c.from(Movimentacao.class);
	    c.select(tabelaDeMovimentacao);
	    List<Predicate> criteria = new ArrayList<Predicate>();

	    if (existe(dataDe)) {
	        ParameterExpression<String> p = cb.parameter(String.class, "data");
	        criteria.add(cb.greaterThan(tabelaDeMovimentacao.<String>get("data"), p));
	    }
	    
	    if (existe(dataAte)) {
	    	ParameterExpression<String> p = cb.parameter(String.class, "data");
	    	criteria.add(cb.lessThan(tabelaDeMovimentacao.<String>get("data"), p));
	    }
	    
	    if (criteria.size() == 0) {
	        throw new RuntimeException("no criteria");
	    } else if (criteria.size() == 1) {
	        c.where(criteria.get(0));
	    } else {
	        c.where(cb.and(criteria.toArray(new Predicate[0])));
	    }

	    TypedQuery<Movimentacao> q = getEm().createQuery(c);

	    if (existe(dataDe)) {
	        q.setParameter("data", dataDe);
	    }
	    
	    if (existe(dataAte)) {
	    	q.setParameter("data", dataAte);
	    }
	    
	    return q.getResultList();
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
