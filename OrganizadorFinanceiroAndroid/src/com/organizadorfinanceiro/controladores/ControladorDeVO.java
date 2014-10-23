package com.organizadorfinanceiro.controladores;

import com.organizadorfinanceiro.abstratas.Classe;

public abstract class ControladorDeVO<T> extends Classe{

	abstract void encriptaVO(T o);
	
}
