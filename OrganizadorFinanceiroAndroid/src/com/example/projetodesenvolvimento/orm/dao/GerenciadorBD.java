/**
 * 
 */
package com.example.projetodesenvolvimento.orm.dao;

import com.example.projetodesenvolvimento.orm.modelos.Usuario;
import com.example.projetodesenvolvimento.utils.Constantes;
import com.example.projetodesenvolvimento.utils.GeradorSQLBean;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * @author Alex
 *
 */
public class GerenciadorBD extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = Constantes.BANCO_DADOS_NOME;
	private static final int DATABASE_VERSION = Constantes.BANCO_DADOS_VERSAO;
	private static final String COMANDO_CRIACAO_TB_RELACIONAMENTOS = "CREATE TABLE TB_RELACIONAMENTOS ("
			+ "\n ID_RELACIONAMENTO INTEGER not null,"
			+ "\n ID_USUARIO INTEGER not null,"
			+ "\n ID_USUARIO_AMIGO INTEGER not null,"
			+ "\n PRIMARY KEY (ID_RELACIONAMENTO));"
			+ "\n "
			+ "\n CREATE INDEX indice on TB_RELACIONAMENTOS (ID_USUARIO, ID_USUARIO_AMIGO)"
			+ "\n ";
	
	public GerenciadorBD(Context contexto) {
		super(contexto, DATABASE_NAME, null, DATABASE_VERSION);
	}

	
	/* (non-Javadoc)
	 * @see com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase, com.j256.ormlite.support.ConnectionSource)
	 */
	@Override
	public void onCreate(SQLiteDatabase bd) {
//		try {
			Log.i(GerenciadorBD.class.getSimpleName(), "onCreate");
			bd.execSQL(COMANDO_CRIACAO_TB_RELACIONAMENTOS);
			bd.execSQL(GeradorSQLBean.getInstancia(Usuario.class).getCreateTable());
			/*bd.execSQL(GeradorSQLBean.getInstancia(UsuarioContato.class).getCreateTable());
			bd.execSQL(GeradorSQLBean.getInstancia(UsuarioEndereco.class).getCreateTable());
			bd.execSQL(GeradorSQLBean.getInstancia(UsuarioSocial.class).getCreateTable());*/
			
//		} catch (SQLException e) {
//			Log.e(BDGerenciador.class.getSimpleName(), "Can't create database", e);
//			throw new RuntimeException(e);
//		}
		
	}

	/* (non-Javadoc)
	 * @see com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase, com.j256.ormlite.support.ConnectionSource, int, int)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase bd, int versaoAnterior, int versaoNova) {
//		try {
		bd.execSQL(GeradorSQLBean.getInstancia(Usuario.class).getDropTable());
		/*bd.execSQL(GeradorSQLBean.getInstancia(UsuarioContato.class).getDropTable());
		bd.execSQL(GeradorSQLBean.getInstancia(UsuarioEndereco.class).getDropTable());
		bd.execSQL(GeradorSQLBean.getInstancia(UsuarioSocial.class).getDropTable());*/
			onCreate(bd);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}

	}

	/**
	 * Close the database connections and clear any cached DAOs.
	 */
	@Override
	public void close() {
		super.close();
	}	
}
