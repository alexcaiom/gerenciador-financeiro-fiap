package com.example.projetodesenvolvimento.orm.dao.finder;

import com.example.projetodesenvolvimento.excecoes.SysErr;
import com.example.projetodesenvolvimento.orm.dao.DAO;

import android.database.sqlite.SQLiteDatabase;

public abstract class Finder<T> extends DAO<T> {

	protected String getNomeEntidade(){
		return CLASSE_NOME.substring(CLASSE_NOME.lastIndexOf("Finder"));
	}
	
	protected SQLiteDatabase getBD() throws SysErr{
		return getBD(TIPO_BD_LEITURA);
	}
	
}
