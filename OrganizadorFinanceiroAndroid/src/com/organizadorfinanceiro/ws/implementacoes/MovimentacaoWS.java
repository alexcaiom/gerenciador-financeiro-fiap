/**
 * 
 */
package com.organizadorfinanceiro.ws.implementacoes;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;

import com.organizadorfinanceiro.excecoes.Erro;
import com.organizadorfinanceiro.orm.modelos.Movimentacao;
import com.organizadorfinanceiro.orm.modelos.Usuario;
import com.organizadorfinanceiro.orm.modelos.enums.TipoMovimento;
import com.organizadorfinanceiro.utils.UtilsData;
import com.organizadorfinanceiro.ws.WSAcoes;

/**
 * @author User
 *
 */
public class MovimentacaoWS extends WSAcoes {

	private static MovimentacaoWS instancia;
	private Context contexto;
	private final String entidade = "movimentacao";
	
	public void cadastrar(Movimentacao movimento) throws Erro {
		String acao = "cadastro";
		try{
			String login 		= movimento.getLogin();
			String descricao 	= movimento.getDescricao();
			String data 		= UtilsData.getDataDdMMYYYY(movimento.getData());
			String tipo 		= movimento.getTipo().getSigla();
			String valor 		= String.valueOf(movimento.getValor().doubleValue());
			
			JSONObject objetoJson = getJSONObject(acao, entidade, login, descricao, data, tipo, valor);
			movimento = new Movimentacao();
			preencheVo(movimento, objetoJson);
			
		}catch (Erro e){
			throw e;
		} catch (Exception e) {
			throw new Erro(e);
		}
	}

	public void alterar(Movimentacao o) throws Erro {
		String acao = "cadastro";
		try{
			String login 		= o.getLogin();
			String descricao 	= o.getDescricao();
			String data 		= UtilsData.getDataDdMMYYYY(o.getData());
			String tipo 		= o.getTipo().getSigla();
			String valor 		= String.valueOf(o.getValor().doubleValue());
			
			JSONObject objetoJson = getJSONObject(acao, entidade, login, descricao, data, tipo, valor);
			o = new Movimentacao();
			preencheVo(o, objetoJson);
			
		}catch (Erro e){
			throw e;
		} catch (Exception e) {
			throw new Erro(e);
		}
	}

	public void excluir(Movimentacao o) throws Erro {
		String acao = "cadastro";
		try{
			String login 		= o.getLogin();
			String descricao 	= o.getDescricao();
			String data 		= UtilsData.getDataDdMMYYYY(o.getData());
			String tipo 		= o.getTipo().getSigla();
			String valor 		= String.valueOf(o.getValor().doubleValue());
			
			JSONObject objetoJson = getJSONObject(acao, entidade, login, descricao, data, tipo, valor);
			o = new Movimentacao();
			preencheVo(o, objetoJson);
			
		}catch (Erro e){
			throw e;
		} catch (Exception e) {
			throw new Erro(e);
		}
	}

	public List<Movimentacao> pesquisarPorLoginETipoMovimento(String login,
			TipoMovimento tipo, Long dataInicioPesquisa, Long dataFimPesquisa) throws Erro {
		String acao = "pesquisa";
		List<Movimentacao> movimentacoes = new ArrayList<Movimentacao>();
		try{
			//JSONObject objetoJson = getJSONObject(acao, entidade, login);
			JSONArray listaJSONObjects = getJSONArray(acao, entidade, login);
			
			//preencheVo(movimentacao, objetoJson);
			
		}catch (Erro e){
			throw e;
		} catch (Exception e) {
			throw new Erro(e);
		}
		
		return movimentacoes;
	}
	
	private void preencheVo(Movimentacao u, JSONObject o) throws Exception{
		if (existe(o) && existe(u)) {
			
		}
	}
	
	public Context getContexto() {
		return contexto;
	}

	public void setContexto(Context contexto) {
		this.contexto = contexto;
	}

	public static MovimentacaoWS getInstancia(Context contexto){
		if (naoExiste(instancia)) {
			instancia = new MovimentacaoWS();
			instancia.setContexto(contexto);
		}
		return instancia;
	}

}
