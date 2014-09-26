package com.example.projetodesenvolvimento;

import android.os.Bundle;
import android.view.Menu;
import android.webkit.WebView;

import com.example.projetodesenvolvimento.abstratas.ClasseActivity;
import com.example.projetodesenvolvimento.utils.Constantes;

public class GraficoActivity extends ClasseActivity {

	WebView wvGrafico ;
	String strURL = "http://chart.apis.google.com/chart?cht=<tipo_grafico>&chs=450x650&chd=t:100,50,115,80,60|10,20,15,30,50&chxt=x,y&chxl=1:|Janeiro|Fevereiro|Marco|Abril|Maio&chxr=0,0,120&chds=0,120&chco=4D89F9&chbh=35,0,15&chg=8.33,0,5,0&chco=0A8C8A,EBB671&chdl=Ganhos|Gastos";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grafico);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.grafico, menu);
		return true;
	}

	@Override
	public void carregarTela() {
		Bundle parametros = getIntent().getExtras();
		
		boolean temParametros = existe(parametros) && !parametros.isEmpty();
		if (temParametros) {
			String tipoGrafico = ""; 
			if (parametros.containsKey(Constantes.PARAMETRO_TIPO_GRAFICO)) {
				tipoGrafico = parametros.getString(Constantes.PARAMETRO_TIPO_GRAFICO);
				strURL = strURL.replace("<tipo_grafico>", tipoGrafico);
			}
		}
		
		
		
		wvGrafico = ( WebView ) findViewById( R.id.grafico_wvGrafico ) ;
		wvGrafico.loadUrl( strURL ) ;
	}

	@Override
	public void carregarEventos() {
		// TODO Auto-generated method stub
		
	}

}
