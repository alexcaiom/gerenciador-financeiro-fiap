/**
 * 
 */
package com.gerenciadorfinanceiro.excecoes;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;



/**
 * @author Alex
 *
 */
@XmlRootElement
public class SysErr extends Erro implements Serializable {

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
