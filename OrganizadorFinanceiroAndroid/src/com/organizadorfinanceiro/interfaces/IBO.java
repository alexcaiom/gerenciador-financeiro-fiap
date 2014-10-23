package com.organizadorfinanceiro.interfaces;

import com.organizadorfinanceiro.excecoes.Erro;

public interface IBO<T> {

	public void inserir(T o) throws Erro;
	public void alterar(T o) throws Erro;
	public void excluir(T o) throws Erro;
	
	
}
