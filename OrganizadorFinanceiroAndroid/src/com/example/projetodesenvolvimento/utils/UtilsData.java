package com.example.projetodesenvolvimento.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.example.projetodesenvolvimento.abstratas.Classe;

public class UtilsData extends Classe{

	private static final String DATA_EXTENSO_MES = "MMMM";
	private static String formatoBR = "d' de 'MMMM' de 'yyyy";

	/**
	 * Metodo que detecta com base na Data atual do servidor o dia da semana
	 * Retorna true se for (sabado/domingo)
	 * 
	 * @return
	 */
	public static boolean ehFinalDeSemana() {
		Calendar dataDeHoje = GregorianCalendar.getInstance();

		boolean ehFinalDeSemana = dataDeHoje.get(GregorianCalendar.DAY_OF_WEEK) == GregorianCalendar.SATURDAY
							||    dataDeHoje.get(GregorianCalendar.DAY_OF_WEEK) == GregorianCalendar.SUNDAY;
		return ehFinalDeSemana;
	}

	/**
	 * Metodo que detecta com base na hora do servidor se estamos no horario
	 * comercial ou nao
	 * 
	 * @return
	 */
	public static String getDataHojeExtenso(Calendar data) {
		SimpleDateFormat format = new SimpleDateFormat(formatoBR);
		return format.format(data.getTime());
	}
	

	public static Calendar getDataAtual(){
		return GregorianCalendar.getInstance();
	}
	
	public static Calendar strToCalendar(String strData) throws Exception {
		Date data = strToDate(strData);
		return dateToCalendar(data);
	}
	
	public static Calendar dateToCalendar(Date data) throws Exception {
		Calendar calendario = GregorianCalendar.getInstance();
		calendario.setTime(data);
		return calendario;
	}
	
	public static Date calendarToDate(Calendar data) throws Exception {
		return data.getTime();
	}

	public static Date strToDate(String str) throws Exception {
		int y;
		int m;
		int d;

		if (!isValidDateFormat(str))
			throw new Exception("Invalid date format");

		if (!isValidDate(str))
			throw new Exception("Invalid date format");

		d = strToInt(str.substring(0, 2));
		m = strToInt(str.substring(3, 5));
		y = strToInt(str.substring(6));

		Calendar data = Calendar.getInstance();

		data.set(y, m - 1, d, 0, 0, 0);

		return data.getTime();
	}

	public static boolean isValidDateFormat(String str) {
		if ((str.length() != 10)
				|| (!((str.charAt(2) == '/') || (str.charAt(2) == '-')))
				|| (!((str.charAt(5) == '/') || (str.charAt(5) == '-')))
				|| (Character.isDigit(str.charAt(0)) == false)
				|| (Character.isDigit(str.charAt(1)) == false)
				|| (Character.isDigit(str.charAt(3)) == false)
				|| (Character.isDigit(str.charAt(4)) == false)
				|| (Character.isDigit(str.charAt(6)) == false)
				|| (Character.isDigit(str.charAt(7)) == false)
				|| (Character.isDigit(str.charAt(8)) == false)
				|| (Character.isDigit(str.charAt(9)) == false))
			return false;
		else
			return true;
	}

	public static boolean isValidDate(String str) throws Exception {
		if (!isValidDateFormat(str))
			throw new Exception("Invalid date format");

		int d = strToInt(str.substring(0, 2));
		int m = strToInt(str.substring(3, 5));
		int y = strToInt(str.substring(6));

		return isValidDay1(y, m, d);
	}

	public static int strToInt(String pStr) {
		try {
			if (pStr == null)
				pStr = "0";

			if (pStr.equals(""))
				pStr = "0";

			pStr = pStr.trim();

			return Integer.parseInt(pStr);
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	private static boolean isValidDay1(int ano, int mes, int dia) {
		// int diasMes[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

		if ((mes > 12) || (mes < 1) || (dia < 1) || (dia > 31) || (ano < 1)) {
			return false;
		}
		int fim = 31;
		switch (mes) {
		// fevereiro

		case 2: {
			if (ano % 4 == 0) // ano bisexto
				fim = 29;
			else
				fim = 28;
		}
			break;
		// meses que tem 30 dias
		case 4:
		case 6:
		case 9:
		case 11:
			fim = 30;
			break;
		}

		if (dia > fim)
			return false;

		return true;

	}
	
	private static String formataData(Date d, String pattern) {
		SimpleDateFormat sd = new SimpleDateFormat();
		sd.applyPattern(pattern);
		return sd.format(d);
	}

	
	public static String getDataExtensoMes(String d) throws Exception {
		if (naoExiste(d) || d.isEmpty()) {
			return formataData(calendarToDate(getDataAtual()), formatoBR);
		}
		return formataData(strToDate(d), DATA_EXTENSO_MES);
	}	
	
	public static String getMesAtualExtenso() throws Exception {
		return formataData(calendarToDate(getDataAtual()), DATA_EXTENSO_MES);
	}	
	
	public static String getDataHojeExtenso() throws Exception{
		return formataData(calendarToDate(getDataAtual()), formatoBR);
	}
	
}