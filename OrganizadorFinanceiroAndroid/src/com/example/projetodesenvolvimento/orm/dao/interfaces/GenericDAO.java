/**
 * 
 */
package com.example.projetodesenvolvimento.orm.dao.interfaces;

import com.example.projetodesenvolvimento.excecoes.SysErr;
import com.example.projetodesenvolvimento.orm.dao.DAO;

import android.database.sqlite.SQLiteDatabase;


/**
 * @author Alex
 *
 */
public abstract class GenericDAO<T>  extends DAO<T> implements IGenericDAO<T> {

	public abstract T incluir(T orm) throws SysErr;
	
	public abstract void atualizar(T orm)  throws SysErr ;
	
	public abstract void excluir(T orm)  throws SysErr;	
	
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
