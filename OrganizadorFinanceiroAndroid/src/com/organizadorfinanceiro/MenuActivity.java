package com.organizadorfinanceiro;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridView;

import com.organizadorfinanceiro.abstratas.ClasseActivity;
import com.organizadorfinanceiro.enums.EnumGraficoTipo;
import com.organizadorfinanceiro.gui.GridItemVO;
import com.organizadorfinanceiro.gui.adaptadores.AdaptadorMenu;
import com.organizadorfinanceiro.utils.Constantes;

public class MenuActivity extends ClasseActivity {

	private AdaptadorMenu adaptador; 
	GridItemVO[] itensPropriedades = 
		{new GridItemVO(R.string.label_calendario,		R.drawable.tela_grid_01_calendario		, getAcaoClique(CalendarioActivity.class)),
		new GridItemVO(R.string.label_backup,			R.drawable.tela_grid_02_bkp				, getAcaoClique(BackupSettingsActivity.class)),
		new GridItemVO(R.string.label_graficos,			R.drawable.tela_grid_03_graficos		, getAcaoCliqueComParametros(GraficosActivity.class)),
		new GridItemVO(R.string.label_categorias,		R.drawable.tela_grid_04_categorias		, getAcaoClique(BackupSettingsActivity.class)),
		new GridItemVO(R.string.label_financas,			R.drawable.tela_grid_05_financas		, getAcaoClique(FinancasActivity.class)),
		new GridItemVO(R.string.label_excluir_dados,	R.drawable.tela_grid_06_excluir_dados	, getAcaoClique(BackupSettingsActivity.class)),
		new GridItemVO(R.string.label_informacoes,		R.drawable.tela_grid_07_informacoes		, getAcaoClique(BackupSettingsActivity.class))		
		};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		carregarTela();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Integer id = item.getItemId();
		switch (id) {
		case R.id.action_logout:
			deslogar();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void carregarTela() {
		adaptador = new AdaptadorMenu(this, itensPropriedades);
		GridView g = (GridView) findViewById(R.id.gridMenu);
		g.setAdapter(adaptador);
		registerForContextMenu(g);
		carregarEventos();
	}

	@Override
	public void carregarEventos() {
		// TODO Auto-generated method stub
		
	}
	
	private OnClickListener getAcaoClique(final Class destino) {
		return new OnClickListener() {
			@Override
			public void onClick(View v) {
				irPara(destino);
			}
		};
	}
	
	private OnClickListener getAcaoCliqueComParametros(final Class destino) {
		return new OnClickListener() {
			@Override
			public void onClick(View v) {
				Bundle parametros = new Bundle();
				parametros.putString(Constantes.PARAMETRO_TIPO_GRAFICO, EnumGraficoTipo.GRAFICO_DE_BARRAS_VERTICAL.getGoogleCode());
				irPara(destino, parametros);
			}
		};
	}


}
