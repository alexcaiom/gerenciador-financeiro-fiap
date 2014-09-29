/**
 * 
 */
package com.example.projetodesenvolvimento.orm.dao;

import android.content.ContentValues;
import android.content.Context;

import com.example.projetodesenvolvimento.excecoes.SysErr;
import com.example.projetodesenvolvimento.orm.dao.interfaces.GenericDAO;
import com.example.projetodesenvolvimento.orm.modelos.Movimentacao;
import com.example.projetodesenvolvimento.utils.UtilsData;

/**
 * @author Alex
 *
 */
public class DAOMovimentacao extends GenericDAO<Movimentacao> {

	private static DAOMovimentacao instancia;
	private Context contexto;
	
	/* (non-Javadoc)
	 * @see com.myip.orm.interfaces.IGenericDAO#inserir(java.lang.Object)
	 */
	@Override
	public Movimentacao incluir(Movimentacao orm) throws SysErr {
		try {
			ContentValues values = new ContentValues();
			values.put("descricao", orm.getDescricao());
			values.put("senha", UtilsData.calendarToString(orm.getData()));
			values.put("sigla", orm.getTipo().getSigla());
			values.put("valor", orm.getValor().doubleValue());

			long insertId = 0;
			insertId = getBD(TIPO_BD_ESCRITA).insert(getNomeTabela(), null,values);

			if(insertId > -1){
				orm.comCodigo(insertId);
			} else {
				throw new SysErr("Erro ao tentar inserir "+getNomeTabela());
			}
			getBD(TIPO_BD_ESCRITA).close();
			
		} catch (android.database.SQLException e) {
			orm = null;
			e.printStackTrace();
		} catch (SysErr e) {
			e.printStackTrace();
		} catch (Exception e) {
			throw new SysErr(e);
		}
	    return orm;
	}

	/* (non-Javadoc)
	 * @see com.myip.orm.interfaces.IGenericDAO#atualizar(java.lang.Object)
	 */
	@Override
	public void atualizar(Movimentacao orm) throws SysErr {
		ContentValues values = new ContentValues();
		values.put("descricao", orm.getDescricao());
		try {
			values.put("senha", UtilsData.calendarToString(orm.getData()));
		} catch (Exception e) {
			throw new Error(e);
		}
		values.put("sigla", orm.getTipo().getSigla());
		values.put("valor", orm.getValor().doubleValue());


		int resultado = getBD(TIPO_BD_ESCRITA).update(getNomeTabela(), values, "codigo=?", new String[]{orm.getCodigo().toString()});
		if(resultado == -1){
			throw new SysErr("Erro ao tentar atualizar "+getNomeTabela());
		}
		getBD(TIPO_BD_ESCRITA).close();
	}

	/* (non-Javadoc)
	 * @see com.myip.orm.interfaces.IGenericDAO#excluir(java.lang.Object)
	 */
	@Override
	public void excluir(Movimentacao orm) throws SysErr {
		int resultado = getBD(TIPO_BD_ESCRITA).delete(orm.getClass().getSimpleName(), "codigo", new String[]{orm.getCodigo().toString()});
		if(resultado == -1){
			throw new SysErr("Erro ao tentar excluir "+getNomeTabela());
		}
		getBD(TIPO_BD_ESCRITA).close();
	}
	
	public static DAOMovimentacao getInstancia(Context contexto){
		if (naoExiste(instancia)) {
			instancia = new DAOMovimentacao();
			instancia.contexto = contexto;
		}
		return instancia;
	}
	
}
