/**
 * 
 */
package com.gerenciadorfinanceiro.excecoes;



/**
 * @author Alex
 *
 */
public class SysErr extends Erro {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7304609136575584725L;

	
	public SysErr(Throwable erro, String descricaoErro) {
		super(descricaoErro);
		super.initCause(new Exception(erro));
	}
	
	public SysErr(String descricaoErro) {
		super(descricaoErro);
	}
	
}
