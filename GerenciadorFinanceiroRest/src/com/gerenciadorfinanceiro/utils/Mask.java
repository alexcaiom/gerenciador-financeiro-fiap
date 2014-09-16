package com.gerenciadorfinanceiro.utils;


public abstract class Mask {

	public static String unmask(String s) {
		return s.replaceAll("[.]", "").replaceAll("[-]", "")
				.replaceAll("[/]", "").replaceAll("[(]", "")
				.replaceAll("[)]", "");
	}

}