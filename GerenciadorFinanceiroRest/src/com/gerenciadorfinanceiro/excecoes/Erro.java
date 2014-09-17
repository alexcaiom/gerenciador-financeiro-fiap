/**
 * 
 */
package com.gerenciadorfinanceiro.excecoes;

import java.io.Serializable;

/**
 * @author Alex
 * Classe Mãe de Erros/Exceções
 */
public class Erro extends Error {

	public String erro;
	public String mensagem;
	
	public Erro() {
		// TODO Auto-generated constructor stub
	}
	public Erro(String descricaoErro) {
		super(descricaoErro);
		this.erro = descricaoErro;
	} 
	
	public Erro(Throwable e, String descricaoErro) {
		super(descricaoErro);
		super.initCause(e);
		this.erro = descricaoErro;
		if (e != null && !e.getMessage().isEmpty()) {
			this.mensagem = e.getMessage();
		}
	}

	public String getErro() {
		return erro;
	}

	public void setErro(String erro) {
		this.erro = erro;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	@Override
	public String toString() {
		return "Erro [erro=" + erro + ", mensagem=" + mensagem + "]";
	}
	
}
