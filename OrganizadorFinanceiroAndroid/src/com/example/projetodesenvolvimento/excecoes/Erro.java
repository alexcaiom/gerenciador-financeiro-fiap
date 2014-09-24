/**
 * 
 */
package com.example.projetodesenvolvimento.excecoes;

/**
 * @author Alex
 * Classe Mãe de Erros/Exceções
 */
public class Erro extends Throwable {

	public Erro(String descricaoErro) {
		super(descricaoErro);
	} 
	
	public Erro(Throwable e, String descricaoErro) {
		super(descricaoErro);
		super.initCause(e);
	}
	public Erro(Throwable e) {
		super(e);
	}
	
}
