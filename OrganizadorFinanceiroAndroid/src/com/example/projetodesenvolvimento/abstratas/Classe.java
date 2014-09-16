/**
 * 
 */
package com.example.projetodesenvolvimento.abstratas;

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
	
}
