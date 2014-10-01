package com.example.projetodesenvolvimento.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import com.example.projetodesenvolvimento.abstratas.Classe;

public class UtilsData extends Classe{

	private static final String DATA_EXTENSO_MES = "MMMM";
	private static String formatoBR = "d' de 'MMMM' de 'yyyy";
	private static String formatoBRTelaListaMovimentos = "EEE - dd/MM/yyyy";
	public static final String[] diasDaSemana = new String[] { "Dom", "Seg", "Ter", "Qua", "Qui", "Sex", "Sab" };
	public static final String[] meses = { "Janeiro", "Fevereiro", "Março",
			"Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro",
			"Outubro", "Novembro", "Dezembro" };
	public static final String[] months = { "January", "February", "March",
		"April", "May", "June", "July", "August", "September",
		"October", "November", "December" };
	
	
	public static final String DATA_EXTENSO = "dd 'de' MMMMM 'de' yyyy";
	public static final String DATA_EXTENSO_DIA_1_CARACTER = "d 'de' MMMMM 'de' yyyy";
	public static final String DATA_EXTENSO_SEMANA = "EEEEE',' " + DATA_EXTENSO;
	public static final String DATA_ANO_4D = "yyyy";
	public static final String DATA_ANO_2D = "yy";
	public static final String DATA_MES_2D = "MM";
	public static final String DATA_MES_ANO_4D = "MMMMM yyyy";
	public static final String DATA_DEFAULT = "dd/MM/yyyy";
	public static final String DATA_COMPLETA = "dd/MM/yyyy HH:mm:ss";
	public static final String DATA_MES_2D_ANO_4D = "MM/yyyy";

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
		SimpleDateFormat format = new SimpleDateFormat(formatoBR, Locale.getDefault());
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
		if (existe(data)) {
			Calendar calendario = GregorianCalendar.getInstance();
			calendario.setTime(data);
			return calendario;
		}
		return null;
	}
	
	public static String dateToString(Date d) throws Exception {
		if (existe(d)) {
			return new SimpleDateFormat(formatoBR, Locale.getDefault()).format(d);
		}
		return null;
	}
	
	public static String calendarToString(Calendar data) throws Exception {
		if (existe(data)) {
			return dateToString(data.getTime());
		}
		return null;
	}
	
	public static String calendarToStringTelaItens(Calendar data) throws Exception {
		if (existe(data)) {
			return new SimpleDateFormat(formatoBRTelaListaMovimentos, Locale.getDefault()).format(data.getTime());
		}
		return null;
	}
	
	public static Date calendarToDate(Calendar data) throws Exception {
		return data.getTime();
	}

	public static Date strToDate(String str) throws Exception {
		if (existe(str)) {
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
		return null;
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
	
	public static Integer getMesNumerico(String strMes){
		
		for (int i = 0; i < meses.length; i++) {
			String mes = meses[i];
			if (mes.equals(strMes)) {
				return i;
			}
		}
		
		return null;
	}
	
	public static String getMonth(int mes){
		return months[mes];
	}
}