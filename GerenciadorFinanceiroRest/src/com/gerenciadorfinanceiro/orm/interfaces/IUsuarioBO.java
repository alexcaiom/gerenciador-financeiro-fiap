/**
 * 
 */
package com.gerenciadorfinanceiro.orm.interfaces;

import com.gerenciadorfinanceiro.excecoes.ErroNegocio;
import com.gerenciadorfinanceiro.excecoes.SysErr;
import com.gerenciadorfinanceiro.orm.model.usuario.Usuario;

/**
 * @author Alex
 *
 */
public interface IUsuarioBO {

	public Usuario inserir(Usuario usuario) throws SysErr, ErroNegocio;
	public void alterar(Usuario usuario) throws SysErr, ErroNegocio;
	public void excluir(Usuario usuario) throws SysErr, ErroNegocio;
	
}
