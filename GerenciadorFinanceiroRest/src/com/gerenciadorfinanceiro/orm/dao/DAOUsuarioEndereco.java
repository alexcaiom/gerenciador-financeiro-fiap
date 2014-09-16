package com.gerenciadorfinanceiro.orm.dao;
/**
 * 
 *//*
package com.myipandroid.orm.dao;

import java.sql.SQLException;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.myipandroid.orm.BDGerenciador;
import com.myipandroid.orm.interfaces.GenericDAO;
import com.myipandroid.orm.interfaces.IUsuarioEnderecoDAO;
import com.myipandroid.orm.model.usuario.UsuarioEndereco;

*//**
 * @author Alex
 *
 *//*
public class DAOUsuarioEndereco extends GenericDAO<UsuarioEndereco> 
					implements IUsuarioEnderecoDAO {
	
	private static DAOUsuarioEndereco instancia;
	private Context contexto;

	 (non-Javadoc)
	 * @see com.myipandroid.orm.interfaces.IGenericDAO#inserir(java.lang.Object)
	 
	@Override
	public void inserir(UsuarioEndereco orm) throws SQLException {
		getDAO().create(orm);
	}

	 (non-Javadoc)
	 * @see com.myipandroid.orm.interfaces.IGenericDAO#atualizar(java.lang.Object)
	 
	@Override
	public UsuarioEndereco atualizar(UsuarioEndereco orm) throws SQLException {
		if(getDAO().update(orm)>0)
			return orm;
		return null;
	}

	 (non-Javadoc)
	 * @see com.myipandroid.orm.interfaces.IGenericDAO#excluir(java.lang.Object)
	 
	@Override
	public void excluir(UsuarioEndereco orm) throws SQLException {
		getDAO().delete(orm);
	}


	@Override
	public Dao<UsuarioEndereco, Integer> getDAO() throws SQLException {
		return new BDGerenciador<UsuarioEndereco>(getContexto()).getDAO(UsuarioEndereco.class);
	}
	
	public static DAOUsuarioEndereco getInstancia(Context contexto){
		if(instancia == null){
			instancia = new DAOUsuarioEndereco();
			instancia.setContexto(contexto);
		}
		return instancia;
	}

	*//**
	 * @return the contexto
	 *//*
	public final Context getContexto() {
		return contexto;
	}
	
	public final void setContexto(Context contexto){
		this.contexto = contexto;
	}
}
*/