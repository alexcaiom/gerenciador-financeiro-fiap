/**
 * 
 */
package com.example.projetodesenvolvimento.ws;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;

/**
 * @author Alex
 *
 */
public class WSAcoes {
	
	private static WSAcoes instancia = null;

	//você precisar colocar o endereco do pc em que encontra seu servidor tomcat
	private final String enderecoServidor = "www.alexcaiom.com.br";
	private final int porta=8081;
	String url = "";
	Map<String, String> params = new HashMap<String, String>();
	WebService webService = null;
	
	Context contexto = null;
	
    /**
     * Pega Lista de Usuarios do Servidor
     */
    public void pesquisarUsuariosPorIPComo(String ip) {
    	url = "http://"+enderecoServidor+/*":"+porta+*/"/MyIPRest/rest/usuario/pesquisarPorIPComo/"+ip;
    	
    	webService = new WebService(url, contexto);
    	
    	
    	//Pega a resposta do servidor
    	String response = webService.webGet("", params);
    	
    	try {
    		//Seta a resposta como um objeto JSON para acessar as informações
    		if(response !=null && !"".equalsIgnoreCase(response)){
    			JSONObject o=new JSONObject(response);
    			String out=o.get("message").toString();
    		}
    	} catch (JSONException e1) {
    		// TODO Auto-generated catch block
    		e1.printStackTrace();
    	}
    }
    
    public JSONArray getJSONArray(String acao, Object...parametros){
    	url = "http://"+enderecoServidor+/*":"+porta+*/"/MyIPRest/rest/usuario/pesquisarPorIPComo/";
    	return null;
    }
    
    public JSONObject getJSONObject(String acao, Object...parametros){
    	return null;
    }
    
    public static WSAcoes getInstancia(Context contexto){
    	if (instancia == null){
    		instancia = new WSAcoes();
    		instancia.contexto = contexto;
    	}
    	return instancia;
    }
    
}
