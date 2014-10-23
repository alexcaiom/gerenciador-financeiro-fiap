/**
 * 
 */
package com.organizadorfinanceiro.abstratas;

import android.util.Log;

/**
 * @author Alex
 *
 */
public abstract class Classe {

	public String CLASSE_NOME = getClass().getSimpleName(); 
	
	/**
	 * Metodo Logger especializado
	 */
	protected void log(String textoParaLog) {
		Log.i(CLASSE_NOME, textoParaLog);
	}
	
	/**
	 * Metodo que verifica se o objeto eh nulo
	 * @param o
	 * @return
	 */
	public static boolean naoExiste(Object o){
		boolean naoExiste = o==null;
		if (o instanceof String) {
			String texto = (String) o;
			naoExiste = texto.equals("null");
		}
		return naoExiste;
	}
	
	/**
	 * Metodo que verifica se o objeto nao eh nulo
	 * @param o
	 * @return
	 */
	public static boolean existe(Object o){
		boolean existe = !naoExiste(o);
		return existe;
	}
	
}
