/**
 * 
 */
package com.organizadorfinanceiro.orm.dao;

import android.content.ContentValues;
import android.graphics.AvoidXfermode;
import android.widget.Toast;

import com.organizadorfinanceiro.abstratas.ClasseActivity;
import com.organizadorfinanceiro.excecoes.SysErr;
import com.organizadorfinanceiro.orm.dao.interfaces.GenericDAO;
import com.organizadorfinanceiro.orm.modelos.Movimentacao;
import com.organizadorfinanceiro.utils.UtilsData;
import com.organizadorfinanceiro.utils.UtilsNumero;

/**
 * @author Alex
 *
 */
public class DAOMovimentacao extends GenericDAO<Movimentacao> {

	private static DAOMovimentacao instancia;
	
	/* (non-Javadoc)
	 * @see com.myip.orm.interfaces.IGenericDAO#inserir(java.lang.Object)
	 */
	@Override
	public Movimentacao incluir(Movimentacao orm) throws SysErr {
		try {
			ContentValues values = new ContentValues();
			values.put("descricao", orm.getDescricao());
			values.put("data", orm.getData().getTimeInMillis());
			values.put("tipo_id", orm.getTipo().getTipo());
			values.put("valor", orm.getValor().doubleValue());
			values.put("login", orm.getLogin());
			
			String mensagem = "Inserindo "+orm.getDescricao() + " - R$ "+UtilsNumero.getMoeda(orm.getValor().doubleValue())+
								" - em "+UtilsData.dateToString(orm.getData().getTime());
			contexto.avisar(mensagem, Toast.LENGTH_SHORT);
			long insertId = 0;
			insertId = incluir(orm, values);

			if(insertId > -1){
				orm.comCodigo(insertId);
			} else {
				throw new SysErr("Erro ao tentar inserir "+getNomeTabela());
			}
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
		values.put("data", orm.getData().getTimeInMillis());
		values.put("tipo_id", orm.getTipo().getTipo());
		values.put("valor", orm.getValor().doubleValue());

		atualizar(values, "codigo=?", new String[]{orm.getCodigo().toString()});
	}

	/* (non-Javadoc)
	 * @see com.myip.orm.interfaces.IGenericDAO#excluir(java.lang.Object)
	 */
	@Override
	public void excluir(Movimentacao orm) throws SysErr {
		excluir("codigo=?", new String[]{orm.getCodigo().toString()});
	}
	
	public static DAOMovimentacao getInstancia(ClasseActivity contexto){
		if (naoExiste(instancia)) {
			instancia = new DAOMovimentacao();
			instancia.contexto = contexto;
		}
		return instancia;
	}
	
}
