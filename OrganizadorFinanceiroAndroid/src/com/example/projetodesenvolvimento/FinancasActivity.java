package com.example.projetodesenvolvimento;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.projetodesenvolvimento.abstratas.ClasseActivity;
import com.example.projetodesenvolvimento.controladores.ControladorDeMovimentacoes;
import com.example.projetodesenvolvimento.gui.adaptadores.AdaptadorListaMovimentos;
import com.example.projetodesenvolvimento.orm.modelos.Movimentacao;
import com.example.projetodesenvolvimento.orm.modelos.Usuario;
import com.example.projetodesenvolvimento.utils.Constantes;
import com.example.projetodesenvolvimento.utils.Sessao;

public class FinancasActivity extends ClasseActivity implements OnClickListener {
	private TextView currentMonth;
	private ImageView prevMonth;
	private ImageView nextMonth;
	private Button addEvent;
	private Calendar calendar;
	private int month, year;
	private static final String dateTemplate = "MMMM yyyy";
	
	private List<Movimentacao> movimentacaoes;
	private ListView lista;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_financas);
		carregarTela();
	}

	private void setGridCellAdapterToDate(int month, int year) {
		calendar.set(year, month - 1, calendar.get(Calendar.DAY_OF_MONTH));
		currentMonth.setText(DateFormat.format(dateTemplate,calendar.getTime()));
	}

	@Override
	public void onClick(View v) {
		if (v == prevMonth) {
			if (month <= 1) {
				month = 12;
				year--;
			} else {
				month--;
			}
			log("Setting Prev Month in GridCellAdapter: " + "Month: "
					+ month + " Year: " + year);
			setGridCellAdapterToDate(month, year);
		}
		if (v == nextMonth) {
			if (month > 11) {
				month = 1;
				year++;
			} else {
				month++;
			}
			log("Setting Next Month in GridCellAdapter: " + "Month: "
					+ month + " Year: " + year);
			setGridCellAdapterToDate(month, year);
		}
		if (v == addEvent) {
			irPara(CadastroMovimentoActivity.class);
		}

	}

	public void onDestroy() {
		log("Destroying View ...");
		super.onDestroy();
	}

	@Override
	public void carregarTela() {
		calendar = Calendar.getInstance(Locale.getDefault());
		month = calendar.get(Calendar.MONTH) + 1;
		year = calendar.get(Calendar.YEAR);
		log("Calendar Instance:= " + "Month: " + month + " " + "Year: "+ year);

		/**
		 * Barra de Navegabilidade de Meses
		 */
		prevMonth 		= (ImageView)	findViewById(R.id.financas_prevMonth);
		currentMonth 	= (TextView) 	findViewById(R.id.financas_currentMonth);
		currentMonth.setText(DateFormat.format(dateTemplate,calendar.getTime()));
		nextMonth 		= (ImageView) 	findViewById(R.id.financas_nextMonth);
		addEvent 		= (Button) 		findViewById(R.id.financas_addEvent);
		
		lista 			= (ListView) 	findViewById(R.id.financas_lista);
		preencheListaMovimentos();
		ocultarBarraDeAcoes();
		carregarEventos();
	}

	private void preencheListaMovimentos() {
		Usuario usuarioLogado = (Usuario) Sessao.getParametro(Constantes.USUARIO);
		movimentacaoes = ControladorDeMovimentacoes.getInstancia(FinancasActivity.this).pesquisarPorLogin(usuarioLogado.getLogin());
		AdaptadorListaMovimentos adaptador = new AdaptadorListaMovimentos(FinancasActivity.this, movimentacaoes);
		lista.setAdapter(adaptador);
	}

	@Override
	public void carregarEventos() {
		prevMonth.setOnClickListener(this);
		nextMonth.setOnClickListener(this);
		addEvent.setOnClickListener(this);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		preencheListaMovimentos();
	}
}
