/**
 * 
 */
package com.example.projetodesenvolvimento.ws;

import java.io.Console;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.projetodesenvolvimento.abstratas.Classe;
import com.example.projetodesenvolvimento.excecoes.Erro;
import com.example.projetodesenvolvimento.utils.Constantes;

import android.app.Activity;
import android.content.Context;

/**
 * @author Alex
 *
 */
public class WSAcoes extends Classe {
	
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
    
    public JSONArray getJSONArray(String acao, String...parametros){
    	url = "http://"+enderecoServidor+/*":"+porta+*/"/MyIPRest/rest/usuario/pesquisarPorIPComo/";
    	return null;
    }
    
    public JSONObject getJSONObject(String acao, String entidade, String...parametros) throws Erro{
    	url = Constantes.CONEXAO_PROTOCOLO+"://"+Constantes.CONEXAO_CONTEXTO+"/"+entidade;
    	
    	for (String p : parametros) {
			url += "/" + p;
		}
    	
    	webService = new WebService(url, contexto);
    	String resposta = webService.webGet("", params);
    	
    	JSONObject o = null;
    	try{
    		boolean aRespostaEhUmJSONOBjectValido = existe(resposta)  && !resposta.isEmpty() && nenhumErroMapeadoDetectado(resposta); 
    		if (aRespostaEhUmJSONOBjectValido) {
				o = new JSONObject(resposta);
			} else {
				throw new Erro(resposta);
			}
    	} catch (JSONException e){
    		e.printStackTrace();
    	}
    	
    	return o;
    }
    
    private boolean nenhumErroMapeadoDetectado(String resposta) {
		
		return false;
	}

	public static WSAcoes getInstancia(Context contexto){
    	if (instancia == null){
    		instancia = new WSAcoes();
    		instancia.contexto = contexto;
    	}
    	return instancia;
    }
    
}
