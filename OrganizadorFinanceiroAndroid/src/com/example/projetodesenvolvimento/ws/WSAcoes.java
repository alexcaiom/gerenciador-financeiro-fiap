/**
 * 
 */
package com.example.projetodesenvolvimento.ws;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;

import com.example.projetodesenvolvimento.R;
import com.example.projetodesenvolvimento.abstratas.Classe;
import com.example.projetodesenvolvimento.abstratas.ClasseActivity;
import com.example.projetodesenvolvimento.excecoes.Erro;
import com.example.projetodesenvolvimento.excecoes.ErroNegocio;
import com.example.projetodesenvolvimento.excecoes.SysErr;
import com.example.projetodesenvolvimento.utils.Constantes;

/**
 * @author Alex
 *
 */
public class WSAcoes extends Classe {
	
	private static WSAcoes instancia = null;

	//você precisar colocar o endereco do pc em que encontra seu servidor tomcat
	private final String enderecoServidor = "177.60.189.183";
//	private final String enderecoServidor = "www.alexcaiom.com.br";
	private final int porta=8081;
	String url = "";
	Map<String, String> params = new HashMap<String, String>();
	WebService webService = null;
	WebServiceTask tarefa = null;
	
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
    	url = Constantes.CONEXAO_PROTOCOLO+"://"+enderecoServidor+"/"+Constantes.CONEXAO_CONTEXTO+"/"+entidade+"/"+acao;
    	
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
    
    private String comunicarComOServidor(String url) throws Erro{
    	if (this.tarefa == null) {
    		this.tarefa = new WebServiceTask();
		}
    	
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
    
    private boolean respostaEstaSemErros(String resposta) throws SysErr {
    	
		boolean contemPalavraException = resposta.contains(Constantes.EXCECAO);
		if (contemPalavraException) {
			throw new SysErr(resposta);
		}
		
		boolean contemPalavraUsuario = resposta.contains("usuario");
		boolean contemPalavraUsuariosParaLista = resposta.contains("usuarios");
		boolean contemPalavraMovimento = resposta.contains("movimento");
		boolean contemPalavraMovimentosParaLista = resposta.contains("movimentos");
		
		boolean respostaSemErros = !contemPalavraException && (contemPalavraUsuario || contemPalavraUsuariosParaLista
															|| contemPalavraMovimento || contemPalavraMovimentosParaLista);
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
    		String resultado = webService.webGet(url, params);
    		
    		return resultado;
    	}
    	
    	@Override
    	protected void onPostExecute(String result) {
    		super.onPostExecute(result);
    	}
    }

	public static WSAcoes getInstancia(Context contexto){
    	if (instancia == null){
    		instancia = new WSAcoes();
    		instancia.contexto = contexto;
    	}
    	return instancia;
    }
    
}
