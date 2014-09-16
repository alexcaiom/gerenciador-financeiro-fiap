/**
 * 
 */
package com.example.projetodesenvolvimento.interfaces;

import com.example.projetodesenvolvimento.excecoes.ErroNegocio;
import com.example.projetodesenvolvimento.excecoes.SysErr;
import com.example.projetodesenvolvimento.orm.modelos.Usuario;


/**
 * @author Alex
 *
 */
public interface IUsuarioBO {

	public void inserir(Usuario usuario) throws SysErr, ErroNegocio;
	public void alterar(Usuario usuario) throws SysErr, ErroNegocio;
	public void excluir(Usuario usuario) throws SysErr, ErroNegocio;
	
}
