/**
 * 
 */
package com.gerenciadorfinanceiro.orm.model;


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
