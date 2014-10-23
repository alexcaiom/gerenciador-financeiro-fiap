/**
 * 
 */
package com.organizadorfinanceiro.orm.dao.interfaces;

import com.organizadorfinanceiro.excecoes.SysErr;
import com.organizadorfinanceiro.orm.dao.DAO;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;


/**
 * @author Alex
 *
 */
public abstract class GenericDAO<T>  extends DAO<T> implements IGenericDAO<T> {

	public Long incluir(T orm, ContentValues values) throws SysErr {
		long insertId = 0;
		try {
			iniciarTransacao();
			getBD().insert(getNomeTabela(), null,values);
			if(insertId == -1){
				throw new SysErr("Erro ao tentar inserir "+getNomeTabela());
			}
			commit();
			finalizarTransacao();
		} catch (SysErr e) {
			orm = null;
			throw e;
		} finally {
			finalizar();
		}
		return insertId;
	}

	public void atualizar(ContentValues values, String whereClause, String[] whereArgs) throws SysErr{
		try{
			iniciarTransacao();
			getBD(TIPO_BD_ESCRITA).update(getNomeTabela(), values, whereClause, whereArgs);
			commit();
			finalizarTransacao();
		} catch (SysErr e){
			throw new SysErr(e, "Erro ao Atualizar "+getNomeTabela());
		} finally {
			finalizar();
		}
	}

	public void excluir(String whereClause, String[] whereArgs) throws SysErr{
		try{
			iniciarTransacao();
			getBD().delete(getNomeTabela(), whereClause, whereArgs);
			commit();
			finalizarTransacao();
		} catch (SysErr e){
			
			throw new SysErr(e, "Erro ao Excluir "+getNomeTabela());
		} finally {
			finalizar();
		}
	}
	
	public SQLiteDatabase getBD()  throws SysErr{
		return getBD(TIPO_BD_ESCRITA);
	}
	
	public void iniciarTransacao()  throws SysErr{
		getBD(TIPO_BD_ESCRITA).beginTransaction();
	}
	
	public void commit() throws SysErr {
		getBD(TIPO_BD_ESCRITA).setTransactionSuccessful();
	}
	
	public void finalizarTransacao() throws SysErr{
		if (getBD(TIPO_BD_ESCRITA).inTransaction()) {
			getBD(TIPO_BD_ESCRITA).endTransaction();
		}
		getBD(TIPO_BD_ESCRITA).close();
	}
}
