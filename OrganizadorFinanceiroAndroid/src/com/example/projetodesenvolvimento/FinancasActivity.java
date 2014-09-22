package com.example.projetodesenvolvimento;

import java.util.Calendar;

import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projetodesenvolvimento.abstratas.ClasseActivity;
import com.example.projetodesenvolvimento.utils.UtilsData;

public class FinancasActivity extends ClasseActivity {
	
	private Calendar data;
	
	Button btnConfiguracoes, btnAddMovimento;
	ImageView imgUltimoMes, imgProximoMes;
	TextView lblEsteMes;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_financas);
		carregarTela();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.financas, menu);
		return true;
	}

	@Override
	public void carregarTela() {
		btnConfiguracoes 	= (Button) findViewById(R.id.financas_settings);
		btnAddMovimento 	= (Button) findViewById(R.id.financas_addEvent);
		imgUltimoMes 		= (ImageView) findViewById(R.id.financas_prevMonth);
		imgProximoMes 		= (ImageView) findViewById(R.id.financas_nextMonth);
		lblEsteMes 			= (TextView) findViewById(R.id.financas_currentMonth);
		String esteMes = "";
		try {
			esteMes = UtilsData.getMesAtualExtenso();
			lblEsteMes.setText(esteMes);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		carregarEventos();
	}

	@Override
	public void carregarEventos() {
		
		
	}

}
