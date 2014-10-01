/**
 * 
 */
package com.example.projetodesenvolvimento.orm.dao.finder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import android.content.Context;
import android.database.Cursor;

import com.example.projetodesenvolvimento.excecoes.SysErr;
import com.example.projetodesenvolvimento.orm.modelos.Categoria;
import com.example.projetodesenvolvimento.orm.modelos.Movimentacao;
import com.example.projetodesenvolvimento.orm.modelos.enums.TipoMovimento;
import com.example.projetodesenvolvimento.utils.GeradorSQLBean;

/**
 * @author Alex
 *
 */
public class FinderMovimentacao extends Finder<Movimentacao>{

	private static FinderMovimentacao instancia;

	public FinderMovimentacao(){}

	public FinderMovimentacao(Context contexto) throws SysErr {
		log("Instanciando...");
		this.contexto = contexto;
		this.bd = getBD();
	}

	public Movimentacao findByNome(Movimentacao o) {
		log("Consultando "+getNomeEntidade());
		try {
			cursor = getBD().query(GeradorSQLBean.getInstancia(Movimentacao.class).getNomeTabela(), null, "descricao=?", new String[]{o.getDescricao()}, null, null, null);

			if(cursor.getCount() > 0 && cursor.getColumnCount() >= 9 && cursor.moveToFirst()){
				preencheVO(cursor, o);
			}else{
				o = null;
			}

			
		} catch (SysErr e) {
			throw new Error(e);
		} catch (Exception e) {
			throw new Error(e);
		} finally {
			finalizar();
		}
		return o;
	}

	public Movimentacao findById(Movimentacao u) {
		log("Consultando "+getNomeEntidade());
		try {
			cursor = getBD().query(GeradorSQLBean.getInstancia(Movimentacao.class).getNomeTabela(), null, 
					"id=?", new String[]{u.getCodigo().toString()}, null, null, null);
			
			if(cursor.getCount() > 0 && cursor.getColumnCount() >= 9 && cursor.moveToFirst()){
				preencheVO(cursor, u);
			}else{
				u = null;
			}
			
		} catch (SysErr e) {
			throw new Error(e);
		} catch (Exception e) {
			throw new Error(e);
		} finally {
			finalizar();
		}
		return u;
	}
	
	public List<Movimentacao> pesquisarPorLogin(String login) {
		List<Movimentacao> lista = new ArrayList<Movimentacao>();
		log("Consultando "+getNomeEntidade());
		try {
//			cursor = getBD().query(GeradorSQLBean.getInstancia(Movimentacao.class).getNomeTabela(), null, "login=?", new String[]{login}, null, null, null);
			cursor = getBD().query(GeradorSQLBean.getInstancia(Movimentacao.class).getNomeTabela(), null, null, null, null, null, null);

			log("Registros encontrados: "+cursor.getCount());
			if(cursor.getCount() > 0 && cursor.getColumnCount() >= 6 && cursor.moveToFirst()){
				while(!cursor.isAfterLast()){
					Movimentacao o = new Movimentacao();
					preencheVO(cursor, o);
					log(o.toString());
					lista.add(o);
					cursor.moveToNext();
				}
			}
		} catch (SysErr e) {
			log(e.getMessage());
		} catch (Exception e) {
			log(e.getMessage());
		} finally {
			finalizar();
		}
		return lista;
	}

	public List<Movimentacao> listar()  {
		List<Movimentacao> lista = new ArrayList<Movimentacao>();
		try {
			cursor = getBD().query(GeradorSQLBean.getInstancia(Movimentacao.class).getNomeTabela(), null, 
					null, null, null, null, null);
			Movimentacao u = null;
			if(cursor.getCount() > 0 && cursor.getColumnCount() >= 9 && cursor.moveToFirst()){
				while(!cursor.isAfterLast()){
					preencheVO(cursor, u);
					lista.add(u);
					cursor.moveToNext();
				}
			}else{
				u = null;
			}

		} catch (SysErr e) {
			throw new Error(e);
		} catch (Exception e) {
			throw new Error(e);
		} finally {
			finalizar();
		}
		return lista;
	}
	
	@Override
	public void preencheVO(Cursor c, Movimentacao o) throws Exception {
		if (existe(c) && existe(o)) {
			o.comCodigo(c.getLong(c.getColumnIndex("codigo")));
			o.comDescricao(c.getString(c.getColumnIndex("descricao")));
			Calendar data = GregorianCalendar.getInstance();
			data.setTimeInMillis(c.getLong(c.getColumnIndex("data")));
			o.comData(data);
			o.comTipo(TipoMovimento.getMovimentoPorTipo(c.getInt(c.getColumnIndex("tipo_id"))));
			o.comValor(new BigDecimal(c.getDouble(c.getColumnIndex("valor"))));
			/*Categoria categoria = new Categoria();
			categoria.setDescricao(c.getString(c.getColumnIndex("fonte_id")));
			o.setCategoria(categoria);*/
		}
	}
	

	public static FinderMovimentacao getInstancia(Context contexto)  {
		if(instancia == null){
			try {
				instancia = new FinderMovimentacao(contexto);
			} catch (SysErr e) {
				e.printStackTrace();
			}
		}
		return instancia;
	}
}
