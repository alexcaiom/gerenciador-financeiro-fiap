/**
 * 
 */
package com.example.projetodesenvolvimento.interfaces;

import com.example.projetodesenvolvimento.excecoes.Erro;
import com.example.projetodesenvolvimento.orm.modelos.Usuario;


/**
 * @author Alex
 *
 */
public interface IUsuarioBO {

	public void inserir(Usuario usuario) throws Erro;
	public void alterar(Usuario usuario) throws Erro;
	public void excluir(Usuario usuario) throws Erro;
	
}
