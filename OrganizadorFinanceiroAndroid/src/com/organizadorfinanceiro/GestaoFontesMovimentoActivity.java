package com.organizadorfinanceiro;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.organizadorfinanceiro.abstratas.ClasseActivity;

public class GestaoFontesMovimentoActivity extends ClasseActivity {

	private EditText 	txtDescricao;
	private Button 		btnAdd;
	private ListView 	lista;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gestao_fontes_movimento);
		carregarTela();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.gestao_fontes_movimento, menu);
		return true;
	}

	@Override
	public void carregarTela() {
		txtDescricao 	= (EditText) findViewById(R.id.movimento_fontes_txtFonte);
		btnAdd 			= (Button) findViewById(R.id.movimento_fontes_btnAddFonte);
		lista 			= (ListView) findViewById(R.id.movimento_fontes_lista);
		ocultarBarraDeAcoes();
		carregarEventos();
	}

	@Override
	public void carregarEventos() {
		btnAdd.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
			}
		});
	}

}
