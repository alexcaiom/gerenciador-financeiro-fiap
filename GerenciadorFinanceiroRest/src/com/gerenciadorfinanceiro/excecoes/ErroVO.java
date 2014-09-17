/**
 * 
 */
package com.gerenciadorfinanceiro.excecoes;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Alex
 * Classe Mãe de Erros/Exceções
 */
@XmlRootElement
public class ErroVO implements Serializable {

	public String erro;
	public String mensagem;
	
	public ErroVO() {}
	
	public void setErro(String erro) {
		this.erro = erro;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
