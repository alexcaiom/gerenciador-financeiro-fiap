/**
 * 
 */
package com.example.projetodesenvolvimento.orm.modelos;

import java.util.GregorianCalendar;

/**
 * @author AlexDell
 *
 */
public class Pessoa {

	private String nome;
	private Integer idade;
	private GregorianCalendar dataDeNascimento;
	
	public Pessoa() {}
	public Pessoa(String nome, Integer idade, GregorianCalendar dataDeNascimento) {
		super();
		this.nome = nome;
		this.idade = idade;
		this.dataDeNascimento = dataDeNascimento;
	}


	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getIdade() {
		return idade;
	}
	public void setIdade(Integer idade) {
		this.idade = idade;
	}
	public GregorianCalendar getDataDeNascimento() {
		return dataDeNascimento;
	}
	public void setDataDeNascimento(GregorianCalendar dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}
	
}
