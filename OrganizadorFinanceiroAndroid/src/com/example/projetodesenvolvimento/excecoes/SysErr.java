/**
 * 
 */
package com.example.projetodesenvolvimento.excecoes;



/**
 * @author Alex
 *
 */
public class SysErr extends Erro {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7304609136575584725L;

	
	public SysErr(Throwable e, String descricaoErro) {
		super(descricaoErro);
		super.initCause(new Exception(e));
	}
	public SysErr(Throwable e) {
		super(e);
	}
	
	public SysErr(String descricaoErro) {
		super(descricaoErro);
	}
	
}
