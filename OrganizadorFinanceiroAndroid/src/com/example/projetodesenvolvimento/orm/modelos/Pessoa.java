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
	private String email;
	
	public Pessoa() {}

	public Pessoa(String nome, Integer idade,
			GregorianCalendar dataDeNascimento, String email) {
		this.nome = nome;
		this.idade = idade;
		this.dataDeNascimento = dataDeNascimento;
		this.email = email;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
