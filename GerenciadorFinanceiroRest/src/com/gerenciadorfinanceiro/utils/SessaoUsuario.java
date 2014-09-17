package com.gerenciadorfinanceiro.utils;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.gerenciadorfinanceiro.orm.model.usuario.Usuario;

public class SessaoUsuario {

	private String loginUsuario;
	private Usuario usuario;
	private Calendar dataUltimaAtividade;
	
	public SessaoUsuario() {}
	
	
	public SessaoUsuario(String loginUsuario, Usuario usuario,
			Calendar calendar) {
		this.loginUsuario = loginUsuario;
		this.usuario = usuario;
		this.dataUltimaAtividade = calendar;
	}

	public String getLoginUsuario() {
		return loginUsuario;
	}
	public void setLoginUsuario(String loginUsuario) {
		this.loginUsuario = loginUsuario;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Calendar getDataUltimaAtividade() {
		return dataUltimaAtividade;
	}
	public void setDataUltimaAtividade(GregorianCalendar dataUltimaAtividade) {
		this.dataUltimaAtividade = dataUltimaAtividade;
	}

}
