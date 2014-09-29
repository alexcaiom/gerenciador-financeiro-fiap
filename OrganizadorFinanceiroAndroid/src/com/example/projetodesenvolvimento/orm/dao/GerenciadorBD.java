/**
 * 
 */
package com.example.projetodesenvolvimento.orm.dao;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.projetodesenvolvimento.orm.modelos.Movimentacao;
import com.example.projetodesenvolvimento.utils.Constantes;
import com.example.projetodesenvolvimento.utils.GeradorSQLBean;


/**
 * @author Alex
 *
 */
public class GerenciadorBD extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = Constantes.BANCO_DADOS_NOME;
	private static final int DATABASE_VERSION = Constantes.BANCO_DADOS_VERSAO;

	public GerenciadorBD(Context contexto) {
		super(contexto, DATABASE_NAME, null, DATABASE_VERSION);
	}

	
	/* (non-Javadoc)
	 * @see com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase, com.j256.ormlite.support.ConnectionSource)
	 */
	@Override
	public void onCreate(SQLiteDatabase bd) {
		try {
			Log.i(GerenciadorBD.class.getSimpleName(), "onCreate");
			bd.execSQL(GeradorSQLBean.getInstancia(Movimentacao.class).getCreateTable());
		} catch (SQLException e) {
			Log.e(GerenciadorBD.class.getSimpleName(), "Can't create database", e);
			throw new RuntimeException(e);
		}
		
	}

	/* (non-Javadoc)
	 * @see com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase, com.j256.ormlite.support.ConnectionSource, int, int)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase bd, int versaoAnterior, int versaoNova) {
		try {
		bd.execSQL(GeradorSQLBean.getInstancia(Movimentacao.class).getDropTable());
		onCreate(bd);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Close the database connections and clear any cached DAOs.
	 */
	@Override
	public void close() {
		super.close();
	}	
}
