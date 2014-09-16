/**
 * 
 */
package com.gerenciadorfinanceiro.abstratas;


/**
 * @author Alex
 *
 */
public abstract class Classe {

	public String CLASSE_NOME = getClass().getSimpleName(); 
	
	/**
	 * Metodo Logger especializado
	 */
	public void log(String textoParaLog) {
		System.out.println(CLASSE_NOME+": "+textoParaLog);
	}
	
}
