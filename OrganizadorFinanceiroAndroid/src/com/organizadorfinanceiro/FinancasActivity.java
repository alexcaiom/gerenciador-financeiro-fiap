package com.organizadorfinanceiro;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import android.graphics.Color;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.organizadorfinanceiro.abstratas.ClasseActivity;
import com.organizadorfinanceiro.controladores.ControladorDeMovimentacoes;
import com.organizadorfinanceiro.excecoes.Erro;
import com.organizadorfinanceiro.gui.adaptadores.AdaptadorListaMovimentos;
import com.organizadorfinanceiro.orm.modelos.Movimentacao;
import com.organizadorfinanceiro.orm.modelos.Usuario;
import com.organizadorfinanceiro.orm.modelos.enums.TipoMovimento;
import com.organizadorfinanceiro.utils.Constantes;
import com.organizadorfinanceiro.utils.Dialogos;
import com.organizadorfinanceiro.utils.Sessao;
import com.organizadorfinanceiro.utils.UtilsData;
import com.organizadorfinanceiro.utils.UtilsNumero;

public class FinancasActivity extends ClasseActivity implements OnClickListener {
	private Button btnAtualizar;
	private TextView currentMonth;
	private ImageView prevMonth;
	private ImageView nextMonth;
	private Button addEvent;
	private Calendar calendar;
	private int month, year;
	private static final String dateTemplate = "MMMM yyyy";
	
	private List<Movimentacao> movimentacaoes;
	private ListView lista;
	
	private TextView lblVlrTotalPago;
	private TextView lblVlrTotal;
	private TextView lblVlrTotalOutros;
	
	private double vlrTotalPago;
	private double vlrTotalGanho;
	private double vlrTotal;
	private double vlrTotalOutros;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_financas);
		carregarTela();
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
		btnAtualizar 		= (Button) 		findViewById(R.id.financas_atualiza);
		prevMonth 			= (ImageView)	findViewById(R.id.financas_prevMonth);
		currentMonth 		= (TextView) 	findViewById(R.id.financas_currentMonth);
		setMesSelecionadoPara(month, year);
		nextMonth 			= (ImageView) 	findViewById(R.id.financas_nextMonth);
		addEvent 			= (Button) 		findViewById(R.id.financas_addEvent);
		
		lista 				= (ListView) 	findViewById(R.id.financas_lista);
		lblVlrTotalPago 	= (TextView) 	findViewById(R.id.financas_lblTotPago);
		lblVlrTotal 		= (TextView) 	findViewById(R.id.financas_lblVlrTotal);
		lblVlrTotalOutros 	= (TextView) 	findViewById(R.id.financas_lblVlrTotOutros);
		
		preencheListaMovimentos();
		ocultarBarraDeAcoes();
		carregarEventos();
	}


	@Override
	public void onClick(View v) {
		if (v == btnAtualizar) {
			preencheListaMovimentos();
		}
		if (v == prevMonth) {
			if (month <= 1) {
				month = 12;
				year--;
			} else {
				month--;
			}
			log("Setting Prev Month in GridCellAdapter: " + "Month: "
					+ month + " Year: " + year);
			setMesSelecionadoPara(month, year);
			preencheListaMovimentos();
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
			setMesSelecionadoPara(month, year);
			preencheListaMovimentos();
		}
		if (v == addEvent) {
			irPara(CadastroMovimentoActivity.class);
		}

	}

	private void preencheListaMovimentos() {
		int diaDePagamento = 25;
		Calendar dataInicioPeriodo = GregorianCalendar.getInstance();
		Calendar dataFimPeriodo = GregorianCalendar.getInstance();
		dataInicioPeriodo.set(year, month-2, diaDePagamento-1);
		dataFimPeriodo.set(year, month-1, diaDePagamento);
//		dataFimPeriodo.add(GregorianCalendar.MONTH, 1);
//		dataFimPeriodo.add(GregorianCalendar.DAY_OF_MONTH, -1);
		Usuario usuarioLogado = (Usuario) Sessao.getParametro(Constantes.USUARIO);
		try {
			UtilsData.calendarToStringDataCompleta(dataInicioPeriodo);
			UtilsData.calendarToStringDataCompleta(dataFimPeriodo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			exibirMensagemExcecao(e);
		}
		try {
			movimentacaoes = ControladorDeMovimentacoes.getInstancia(FinancasActivity.this).pesquisarPorLoginETipoMovimento(usuarioLogado.getLogin(), null, dataInicioPeriodo.getTimeInMillis(), dataFimPeriodo.getTimeInMillis());
		} catch (Erro e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			exibirMensagemExcecao(e);
		}
		AdaptadorListaMovimentos adaptador = new AdaptadorListaMovimentos(FinancasActivity.this, movimentacaoes);
		lista.setAdapter(adaptador);
		vlrTotal = 0;
		vlrTotalOutros = 0;
		vlrTotalPago = 0;
		vlrTotalGanho = 0;
		for (Movimentacao m : movimentacaoes) {
			boolean movimentoEhPagamento = m.getTipo().equals(TipoMovimento.DEBITO);
			if (movimentoEhPagamento) {
				vlrTotalPago += m.getValor().doubleValue();
			}
			
//			boolean movimentoEhOutros = movimentoEhPagamento &&
			boolean movimentoEhRecebimento = m.getTipo().equals(TipoMovimento.CREDITO);
			if (movimentoEhRecebimento) {
				vlrTotalGanho += m.getValor().doubleValue();
			}
			
			vlrTotalOutros += m.getValor().doubleValue();
			
		}		
		
		lblVlrTotalPago.setText(UtilsNumero.getMoeda(vlrTotalPago));
//		lblVlrTotalOutros.setText(UtilsNumero.getMoeda(vlrTotalOutros));
		vlrTotal = vlrTotalGanho - vlrTotalPago;
		lblVlrTotal.setText(UtilsNumero.getMoeda(vlrTotal));
		
		boolean estaNegativado = vlrTotal < 0;
		boolean estaBem = vlrTotal > 0;
		if (estaNegativado) {
			lblVlrTotal.setTextColor(Color.RED);
		} else if (estaBem) {
			lblVlrTotal.setTextColor(Color.GREEN);
		} else {
			lblVlrTotal.setTextColor(Color.GRAY);
		}
		
		avisar("Lista de movimentos de "+currentMonth.getText().toString() + " preenchida", Toast.LENGTH_SHORT);
	}

	@Override
	public void carregarEventos() {
		prevMonth.setOnClickListener(this);
		nextMonth.setOnClickListener(this);
		addEvent.setOnClickListener(this);
	}
	
	@Override
	protected void onResume() {
		preencheListaMovimentos();
		super.onResume();
	}
	
	private void setMesSelecionadoPara(int month, int year) {
		calendar.set(year, month - 1, calendar.get(Calendar.DAY_OF_MONTH));
		currentMonth.setText(UtilsData.getMes(month)+ " "+year);
	}
}
