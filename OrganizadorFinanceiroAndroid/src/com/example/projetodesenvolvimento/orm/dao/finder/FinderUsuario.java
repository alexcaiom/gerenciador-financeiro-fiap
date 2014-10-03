/**
 * 
 */
package com.example.projetodesenvolvimento.orm.dao.finder;

import android.database.Cursor;

import com.example.projetodesenvolvimento.abstratas.ClasseActivity;
import com.example.projetodesenvolvimento.excecoes.SysErr;
import com.example.projetodesenvolvimento.orm.modelos.Usuario;

/**
 * @author Alex
 *
 */
public class FinderUsuario extends Finder<Usuario>{

	private static FinderUsuario instancia;

	public FinderUsuario(){}

	public FinderUsuario(ClasseActivity contexto) throws SysErr {
		log("Instanciando...");
		this.contexto = contexto;
		this.bd = getBD();
	}

	/*public Usuario findByNome(Usuario u) {
		log("Consultando "+getNomeEntidade());
		try {
			//		String sql = "select * from tb_usuario where id = "+u.getIp();//GeradorSQLBean.getInstancia(u.getClass()).getCreateTable();

			cursor = getBD().query(GeradorSQLBean.getInstancia(Usuario.class).getNomeTabela(), null, 
					"ip=?", new String[]{u.getIp()}, null, null, null);

			if(cursor.getCount() > 0 && cursor.getColumnCount() >= 9 && cursor.moveToFirst()){
				u = cursorToUsuario(cursor);
			}else{
				u = null;
			}

			
		} catch (SysErr e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			finalizar();
		}
		return u;
	}

	public Usuario findById(Usuario u) {
		log("Consultando "+getNomeEntidade());
		try {
			//		String sql = "select * from tb_usuario where id = "+u.getIp();//GeradorSQLBean.getInstancia(u.getClass()).getCreateTable();
			
			cursor = getBD().query(GeradorSQLBean.getInstancia(Usuario.class).getNomeTabela(), null, 
					"id=?", new String[]{u.getId().toString()}, null, null, null);
			
			if(cursor.getCount() > 0 && cursor.getColumnCount() >= 9 && cursor.moveToFirst()){
				u = cursorToUsuario(cursor);
			}else{
				u = null;
			}
			
		} catch (SysErr e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			finalizar();
		}
		return u;
	}
	
	public static FinderUsuario getInstancia(Context contexto)  {
		if(instancia == null){
			try {
				instancia = new FinderUsuario(contexto);
			} catch (SysErr e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return instancia;
	}

	public List<Usuario> listar()  {
		List<Usuario> lista = new ArrayList<Usuario>();
		try {
			cursor = getBD().query(GeradorSQLBean.getInstancia(Usuario.class).getNomeTabela(), null, 
					null, null, null, null, null);
			Usuario u = null;
			if(cursor.getCount() > 0 && cursor.getColumnCount() >= 9 && cursor.moveToFirst()){
				while(!cursor.isAfterLast()){
					u = cursorToUsuario(cursor);
					lista.add(u);
					cursor.moveToNext();
				}
			}else{
				u = null;
			}

		} catch (SysErr e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			finalizar();
		}
		return lista;
	}
	

	private Usuario cursorToUsuario(Cursor cursor) {
		Usuario usuario = new Usuario();
		usuario.setId(cursor.getInt(cursor.getColumnIndex("id")));
		usuario.setVisualizacaoEndereco(EnumUsuarioVisualizacao.getVisualizacao(cursor.getInt(cursor.getColumnIndex("visualizacaoEndereco"))));//10
		usuario.setVisualizacaoContato(EnumUsuarioVisualizacao.getVisualizacao(cursor.getInt(cursor.getColumnIndex("visualizacaoContato"))));//9
		usuario.setVisualizacaoSocial(EnumUsuarioVisualizacao.getVisualizacao(cursor.getInt(cursor.getColumnIndex("visualizacaoSocial"))));//2
		usuario.setIp(cursor.getString(3));
		usuario.setNome(cursor.getString(4));
		usuario.setSobrenome(cursor.getString(cursor.getColumnIndex("sobrenome")));//6
		usuario.setSenha(cursor.getString(5));
		usuario.setStatus(cursor.getString(8));
		cursor.getColumnName(1);
		cursor.getColumnCount();
		return usuario;
	}*/
	
	@Override
	void preencheVO(Cursor c, Usuario o) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
