/**
 * 
 */
package com.gerenciadorfinanceiro.orm.interfaces;

import com.gerenciadorfinanceiro.excecoes.SysErr;
import com.gerenciadorfinanceiro.orm.model.usuario.Usuario;

/**
 * @author Alex
 *
 */
public interface IUsuarioBO {

	public Object inserir(Usuario usuario) throws SysErr;
	public void alterar(Usuario usuario) throws SysErr;
	public void excluir(Usuario usuario) throws SysErr;
	
}
