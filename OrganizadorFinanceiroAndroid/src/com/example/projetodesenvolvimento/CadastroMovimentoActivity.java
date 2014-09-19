package com.example.projetodesenvolvimento;

import com.example.projetodesenvolvimento.abstratas.ClasseActivity;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class CadastroMovimentoActivity extends ClasseActivity {

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastro);
		/**Verificamos se realizaremos uma alteracao de movimento
		 * ou se apenas cadastraremos um novo
		 */
		verificaParametros(getIntent().getExtras());
		carregarTela();
	}

	private void verificaParametros(Bundle extras) {
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cadastro, menu);
		return true;
	}

	@Override
	public void carregarTela() {
		
		carregarEventos();
	}

	@Override
	public void carregarEventos() {
		// TODO Auto-generated method stub
		
	}

}
