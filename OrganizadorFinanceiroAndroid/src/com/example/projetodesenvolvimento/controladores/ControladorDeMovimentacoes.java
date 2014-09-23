/**
 * 
 */
package com.example.projetodesenvolvimento.controladores;

import com.example.projetodesenvolvimento.orm.modelos.Movimentacao;

import android.content.Context;

/**
 * @author Alex
 *
 */
public class ControladorDeMovimentacoes extends ControladorDeVO<Movimentacao> {

	private static ControladorDeMovimentacoes instancia;
	private Context contexto;
	
	public void cadastrar(Movimentacao m){
		
	}
	
	public static ControladorDeMovimentacoes getInstancia(Context contexto){
		if (naoExiste(instancia)) {
			instancia = new ControladorDeMovimentacoes();
			instancia.setContexto(contexto);
		}
		return instancia;
	}


	@Override
	void encriptaVO(Movimentacao o) {
		// TODO Auto-generated method stub
		
	}


	public Context getContexto() {
		return contexto;
	}


	public void setContexto(Context contexto) {
		this.contexto = contexto;
	}
	
}
