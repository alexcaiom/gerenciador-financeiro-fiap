/**
 * 
 */
package com.gerenciadorfinanceiro.orm.bo;

import java.util.ArrayList;
import java.util.List;

import com.gerenciadorfinanceiro.excecoes.SysErr;
import com.gerenciadorfinanceiro.orm.dao.DAOUsuarioSocial;
import com.gerenciadorfinanceiro.orm.dao.FinderUsuarioSocial;
import com.gerenciadorfinanceiro.orm.model.usuario.Usuario;
import com.gerenciadorfinanceiro.orm.model.usuario.UsuarioSocial;

/**
 * @author Alex
 *
 */
public class BOSocial extends BO<UsuarioSocial> {

	private static BOSocial instancia;
	private DAOUsuarioSocial dao;

	private BOSocial(){}

	public List<UsuarioSocial> listarRedesSociais(){
		log("Listando "+getNomeEntidade()+"s");
		List<UsuarioSocial> redesSociais = new ArrayList<UsuarioSocial>();
		redesSociais = FinderUsuarioSocial.getInstancia().listar();
		return redesSociais;
	}

	public static BOSocial getInstancia() {
		if(instancia == null){
			instancia = new BOSocial();
		}
		return instancia;
	}

	@Override
	public void gravar(List<UsuarioSocial> redesSociais) {
		log("Inserindo "+getNomeEntidade());
		try {
			if(redesSociais != null){
				for (UsuarioSocial rede : redesSociais) {
					if(rede.getId() != null && FinderUsuarioSocial.getInstancia().find(rede.getId()) != null){
						alterar(rede);
					} else {
						getDao().inserir(rede);
					}
				}
			}

		} catch (SysErr e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void alterar(UsuarioSocial usuario) throws SysErr {
		log("Alterando "+getNomeEntidade());
		getDao().atualizar(usuario);
		log(getNomeEntidade()+" salvo(a) com sucesso!");
	}

	@Override
	public void excluir(List<UsuarioSocial> redesSociais) throws SysErr {
		log("Excluindo "+getNomeEntidade());
		for (UsuarioSocial rede : redesSociais) {
			DAOUsuarioSocial.getInstancia().excluir(rede);
		}

	}
	
	public List<UsuarioSocial> pesquisarPorUsuario(Usuario u) {
		log("Pesquisando "+getNomeEntidade()+"(s) por Usuário");
		List<UsuarioSocial> redesSociais = new ArrayList<UsuarioSocial>();
		redesSociais = FinderUsuarioSocial.getInstancia().findByUsuario(u.getId());
		return redesSociais;
	}

	/**
	 * @return the dao
	 */
	public final DAOUsuarioSocial getDao() {
		if(this.dao == null){
			this.dao = DAOUsuarioSocial.getInstancia();
		}
		return dao;
	}

	/**
	 * @param dao the dao to set
	 */
	public final void setDao(DAOUsuarioSocial dao) {
		this.dao = dao;
	}
}
