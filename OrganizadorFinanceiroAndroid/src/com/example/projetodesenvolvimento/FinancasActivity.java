package com.example.projetodesenvolvimento;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projetodesenvolvimento.abstratas.ClasseActivity;
import com.example.projetodesenvolvimento.controladores.ControladorDeMovimentacoes;
import com.example.projetodesenvolvimento.orm.modelos.Movimentacao;
import com.example.projetodesenvolvimento.utils.UtilsData;

public class FinancasActivity extends ClasseActivity implements OnClickListener {
	private Button selectedDayMonthYearButton;
	private TextView currentMonth;
	private ImageView prevMonth;
	private ImageView nextMonth;
	private Button addEvent;
	private Calendar calendar;
	private int month, year;
	private final DateFormat dateFormatter = new DateFormat();
	private static final String dateTemplate = "MMMM yyyy";
	
	View dialogo = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_financas);

		calendar = Calendar.getInstance(Locale.getDefault());
		month = calendar.get(Calendar.MONTH) + 1;
		year = calendar.get(Calendar.YEAR);
		log("Calendar Instance:= " + "Month: " + month + " " + "Year: "+ year);

		prevMonth = (ImageView) this.findViewById(R.id.financas_prevMonth);
		prevMonth.setOnClickListener(this);

		currentMonth = (TextView) this.findViewById(R.id.financas_currentMonth);
		currentMonth.setText(DateFormat.format(dateTemplate,calendar.getTime()));

		nextMonth = (ImageView) this.findViewById(R.id.financas_nextMonth);
		nextMonth.setOnClickListener(this);
		
		addEvent = (Button) findViewById(R.id.financas_addEvent);
		addEvent.setOnClickListener(this);

		dialogo = getLayoutInflater().inflate(R.layout.dialogo_opcoes_movimentacao, null);
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void carregarEventos() {
		// TODO Auto-generated method stub
		
	}
	
	private synchronized View getDialogo(){
		return dialogo;
	}
	
	private synchronized void setDialogo(View dialogo){
		this.dialogo = dialogo;
	}
	
	private synchronized void fecharDialogo(){
		getDialogo().setVisibility(View.GONE);
		setDialogo(null);
	}
}
