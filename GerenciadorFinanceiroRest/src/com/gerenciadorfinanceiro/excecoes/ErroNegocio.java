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
@SuppressWarnings("serial")
@XmlRootElement
public class ErroNegocio extends Erro implements Serializable {

	public ErroNegocio() {
		// TODO Auto-generated constructor stub
	}
	
	public ErroNegocio(String erro) {
		super(erro);
	}
	
}
