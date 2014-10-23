package com.organizadorfinanceiro;

import java.util.List;

import com.organizadorfinanceiro.abstratas.ClasseActivity;
import com.organizadorfinanceiro.orm.modelos.Movimentacao;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;

public class ListaMovimentoActivity extends ClasseActivity {

	private List<Movimentacao> movimentacoes;
	private ListView lista;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_movimento);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lista_movimento, menu);
		return true;
	}

	@Override
	public void carregarTela() {
		
		ocultarBarraDeAcoes();
	}

	@Override
	public void carregarEventos() {
		// TODO Auto-generated method stub
		
	}

}
