package com.gerenciadorfinanceiro.utils;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class UtilsData {

	/**
	 * Metodo que detecta com base na Data atual do servidor o dia da semana
	 * Retorna true se for (sabado/domingo)
	 * @return
	 */
	public static boolean ehFinalDeSemana() {
		Calendar dataDeHoje = GregorianCalendar.getInstance();
		
		boolean ehFinalDeSemana = dataDeHoje.get(GregorianCalendar.DAY_OF_WEEK) == GregorianCalendar.SATURDAY
							||    dataDeHoje.get(GregorianCalendar.DAY_OF_WEEK) == GregorianCalendar.SUNDAY;
		return ehFinalDeSemana;
	}

	/**
	 * Metodo que detecta com base na hora do servidor
	 * se estamos no horario comercial ou nao
	 * @return
	 */
	public static boolean ehHorarioComercial() {
		Calendar horarioAtual = GregorianCalendar.getInstance();
		boolean ehHorarioComercial = (horarioAtual.get(GregorianCalendar.HOUR_OF_DAY) > Constantes.HORARIO_MINIMO_HORA)
								&&
									 (horarioAtual.get(GregorianCalendar.HOUR_OF_DAY) < Constantes.HORARIO_MAXIMO_HORA);
		return ehHorarioComercial;
	}

	
	
}
