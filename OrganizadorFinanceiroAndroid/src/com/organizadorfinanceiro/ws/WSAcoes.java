/**
 * 
 */
package com.organizadorfinanceiro.ws;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;

import com.organizadorfinanceiro.abstratas.Classe;
import com.organizadorfinanceiro.excecoes.Erro;
import com.organizadorfinanceiro.excecoes.ErroNegocio;
import com.organizadorfinanceiro.excecoes.SysErr;
import com.organizadorfinanceiro.utils.Constantes;

/**
 * @author Alex
 *
 */
public class WSAcoes extends Classe {
	
	private static WSAcoes instancia = null;

	//você precisar colocar o endereco do pc em que encontra seu servidor tomcat
//	protected final String enderecoServidor = "152.251.102.247";
//	protected final String enderecoServidor = "177.60.189.183";
//	protected final String enderecoServidor = "192.168.0.15";
	protected final String enderecoServidor = "alexcaiom.com.br";
	protected final String porta="";
//	protected final String porta=":8080";
//	protected final String porta=":8081";
//	protected final int porta=8081;
	protected String url = "";
	Map<String, String> params = new HashMap<String, String>();
	WebService webService = null;
	WebServiceTask tarefa = null;
	
	Context contexto = null;
	
    /**
     * Pega Lista de Usuarios do Servidor
     */
    /*public void pesquisarUsuariosPorIPComo(String ip) {
    	url = "http://"+enderecoServidor+":"+porta+"/MyIPRest/rest/usuario/pesquisarPorIPComo/"+ip;
    	
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
    }*/
    
    public JSONArray getJSONArray(String acao, String entidade, String...parametros) throws Erro{
    	url = Constantes.CONEXAO_PROTOCOLO+"://"+enderecoServidor+porta+"/"+Constantes.CONEXAO_CONTEXTO+"/"+entidade+"/"+acao;

    	for (String p : parametros) {
    		url += "/" + p;
    	}

    	String resposta = comunicarComOServidor(url);

    	JSONArray array = null;
    	try{
    		boolean aRespostaEhUmJSONArrayValido = existe(resposta)  && !resposta.isEmpty() && respostaEstaSemErros(resposta); 
    		if (aRespostaEhUmJSONArrayValido) {
    			array = new JSONArray(resposta);
    		} else {
    			throw new ErroNegocio(resposta);
    		}
    	} catch (JSONException e){
    		e.printStackTrace();
    	}

    	return array;
    }
    
    public JSONObject getJSONObject(String acao, String entidade, String...parametros) throws Erro{
    	url = Constantes.CONEXAO_PROTOCOLO+"://"+enderecoServidor+porta+"/"+Constantes.CONEXAO_CONTEXTO+"/"+entidade+"/"+acao;
    	
    	for (String p : parametros) {
			url += "/" + p;
		}

    	String resposta = comunicarComOServidor(url);
    	
    	JSONObject o = null;
    	try{
    		boolean aRespostaEhUmJSONOBjectValido = existe(resposta)  && !resposta.isEmpty() && respostaEstaSemErros(resposta); 
    		if (aRespostaEhUmJSONOBjectValido) {
				o = new JSONObject(resposta);
			} else {
				throw new ErroNegocio(resposta);
			}
    	} catch (JSONException e){
    		e.printStackTrace();
    	}
    	
    	return o;
    }
    
    protected String comunicarComOServidor(String url) throws Erro{
    	this.tarefa = new WebServiceTask();
    	
    	Map<String, String> dados = new HashMap<String, String>();
    	dados.put("URL", url);
    	
    	String resultadoExecucao = null;
		try {
			resultadoExecucao = tarefa.execute(dados).get();
		} catch (InterruptedException e) {
			throw new SysErr(e);
		} catch (ExecutionException e) {
			throw new SysErr(e);
		} catch (Exception e) {
			throw new Erro(e);
		}
    	
    	return resultadoExecucao;
    }
    
    private boolean respostaEstaSemErros(String resposta) throws SysErr, ErroNegocio {
    	
		boolean contemPalavraException = resposta.contains(Constantes.EXCECAO);
		if (contemPalavraException) {
			throw new SysErr(resposta);
		}
		boolean contemPalavraErro = resposta.contains("\"erro\"");
		if (contemPalavraErro) {
			throw new ErroNegocio(resposta);
		}
		
		boolean contemTagHTML = resposta.contains("<html>");
		if (contemTagHTML) {
			throw new SysErr(resposta);
		}
		
		boolean contemConexaoRecusada = resposta.contains("Conexão") && resposta.contains("recusada");
		
		boolean respostaSemErros = !contemConexaoRecusada && !contemPalavraException;
		return respostaSemErros;
	}
    
    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class WebServiceTask extends AsyncTask<Map<String, String>, Void, String> {
    	@Override
    	protected String doInBackground(Map<String, String>... dados) {
    		String url = (String) dados[0].get("URL");
    		webService = new WebService(url, contexto);
    		String resultado = "";
			try {
				resultado = webService.webGet(url, params);
			} catch (Throwable e) {
				if (existe(e) && existe(e.getMessage())) {
					resultado = e.getMessage();
				}
			}
    		
    		return resultado;
    	}
    	
    	@Override
    	protected void onPostExecute(String result) {
    		super.onPostExecute(result);
    	}
    }
    
    public String getJSonStringSeExistir(JSONObject o, String chave) throws JSONException{
    	if (existe(o) && existe(chave)) {
			if (o.has(chave)) {
				return o.getString(chave);
			}
		}
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
