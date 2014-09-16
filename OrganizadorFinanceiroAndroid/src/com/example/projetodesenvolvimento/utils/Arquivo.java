package com.example.projetodesenvolvimento.utils;

import java.io.FileOutputStream;

import android.graphics.Bitmap;

public class Arquivo {

	public static void salvarImagem(Bitmap bmp, String nomeArquivo){
    	FileOutputStream out = null;
    	try {
    		out = new FileOutputStream(nomeArquivo);
    		bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    		try{
    			if(out != null){
    				out.close();
    			}
    		} catch(Throwable ignore) {}
    	}
    }
	
}
