package com.example.projetodesenvolvimento.controladores;

import com.example.projetodesenvolvimento.abstratas.Classe;

public abstract class ControladorDeVO<T> extends Classe{

	abstract void encriptaVO(T o);
	
}
