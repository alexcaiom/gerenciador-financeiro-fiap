package com.example.projetodesenvolvimento.gui;

import android.view.View.OnClickListener;

public class GridItemVO {

	private int idImagem;
	private int idLabel;
	private OnClickListener acaoClique;
	
	public GridItemVO() {}

	public GridItemVO(int idLabel, int idImagem, OnClickListener acaoClique) {
		super();
		this.idImagem = idImagem;
		this.idLabel = idLabel;
		this.acaoClique = acaoClique;
	}

	public int getIdImagem() {
		return idImagem;
	}

	public void setIdImagem(int idImagem) {
		this.idImagem = idImagem;
	}

	public int getIdLabel() {
		return idLabel;
	}

	public void setIdLabel(int idLabel) {
		this.idLabel = idLabel;
	}

	public OnClickListener getAcaoClique() {
		return acaoClique;
	}

	public void setAcaoClique(OnClickListener acaoClique) {
		this.acaoClique = acaoClique;
	}

	
}
