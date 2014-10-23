package com.organizadorfinanceiro.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class UtilsNumero {

	public final static String MASK_DECIMAL = "#,##0.00";
	public final static String MASK_DECIMAL_4DECIMAIS = "#,##0.0000";
	public final static String MASK_DOUBLE = "###0.00";
	public final static String MASK_DOUBLE_4DECIMAIS = "###0.0000";
	public final static String MASK_MOEDA = "� #,##0.00";
	public final static String MASK_INTEIRO = "#####";
	public final static String MASK_MILHAR_INTEIRO = "###,##0";
	public final static Locale BRASIL = new Locale("pt", "BR");
	public final static Locale USA = Locale.US;
	
	public static String getMoeda(double valor){
		String moeda = "R$ "+ formataDecimalBrasil(valor);
		return moeda;
	}
	
	public static String formataDecimalBrasil(double num) {
		return formataNumero(num, MASK_DECIMAL, BRASIL);
	}
	

	public static String formataNumero(double num, String mask, Locale locale) {

		NumberFormat nf = NumberFormat.getInstance(locale);
		DecimalFormat decFormat = (DecimalFormat) nf;
		decFormat.applyPattern(mask);
		String aux;
		try {
			aux = decFormat.format(num);
		} catch (NumberFormatException n) {
			aux = "0";
		}
		return aux;
	}
	

}
