/**
 * 
 */
package com.gerenciadorfinanceiro.orm.model;

import com.gerenciadorfinanceiro.utils.Constantes;

/**
 * @author Alex
 *
 */
public enum TipoContato {

	CELULAR					(1, "Celular"				),
	CASA					(2, "Casa"					),
	TRABALHO				(3, "Trabalho"				),
	E_MAIL_PESSOAL			(4, "E-mail Pesssoal"		),
	E_MAIL_PROFISSIONAL		(5, "E-mail Profissional"	);
	
	private int cod;
	private String descricao;
	private String mensagem;
	
	private TipoContato(int cod, String descricao){
		this.cod = cod;
		this.descricao = descricao;
		if(cod == 1 || cod == 2 || cod == 3){
			this.mensagem = Constantes.DISCAR_PARA;	
		} else if(cod == 4 || cod == 5) {
			this.mensagem = Constantes.ENVIAR_MENSAGEM_PARA;
		}
	}

	/**
	 * @return the cod
	 */
	public final int getCod() {
		return cod;
	}

	/**
	 * @return the descricao
	 */
	public final String getDescricao() {
		return descricao;
	}
	
	/**
	 * @return the mensagem
	 */
	public final String getMensagem() {
		return mensagem;
	}

	public static TipoContato getTipoContato(int cod){
		for(TipoContato t: TipoContato.values()){
			if(t.cod == cod){
				return t;
			}
		}
		return null;
	}
}
