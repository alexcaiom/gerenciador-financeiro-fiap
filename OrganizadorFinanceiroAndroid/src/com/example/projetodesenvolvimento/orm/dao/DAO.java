package com.example.projetodesenvolvimento.orm.dao;

import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;

import com.example.projetodesenvolvimento.abstratas.Classe;
import com.example.projetodesenvolvimento.excecoes.SysErr;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class DAO<T> extends Classe {
	
	public final Class<T> persistentClass;
	public Context contexto;
	public GerenciadorBD gerenciador;
	public SQLiteDatabase bd;
	public Cursor cursor;
	public static final int TIPO_BD_LEITURA = 1;
	public static final int TIPO_BD_ESCRITA = 2;
	public int tipoBD = 0;
	
	@SuppressWarnings("unchecked")
	public DAO() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	public SQLiteDatabase getBD(int tipo) throws SysErr {
		if(this.bd == null || !this.bd.isOpen()){
			this.tipoBD = tipo;
			switch (tipo) {
			case TIPO_BD_ESCRITA:
				this.bd = getGerenciador().getWritableDatabase();
				break;
			case TIPO_BD_LEITURA:
				this.bd = getGerenciador().getReadableDatabase();
			default:
				break;
			}
		}
		return this.bd;
	}
	
	public void setBD(SQLiteDatabase bd){
		this.bd = bd;
	}
	
	public String getNomeTabela() {
		return new StringBuilder("tb_").append(persistentClass.getSimpleName()).toString();
	}
	

	/**
	 * @return the contexto
	 */
	public final Context getContexto() {
		return contexto;
	}
	
	public final void setContexto(Context contexto){
		this.contexto = contexto;
	}
	
	/**
	 * @return the gerenciador
	 */
	public final GerenciadorBD getGerenciador() {
		if(this.gerenciador == null){
			this.gerenciador = new GerenciadorBD(getContexto());
		}
		return gerenciador;
	}

	/**
	 * @param gerenciador the gerenciador to set
	 */
	public final void setGerenciador(GerenciadorBD gerenciador) {
		this.gerenciador = gerenciador;
	}

	/**
	 * Finaliza o cursor aberto para consultas
	 * e o Banco de Dados
	 * @throws SQLException
	 */
	public final void finalizar() {
		try {
			if(cursor != null){
				cursor.close();
				cursor = null;
			}
			getBD(tipoBD).close();
			setBD(null);
			setGerenciador(null);
		} catch (SysErr e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
