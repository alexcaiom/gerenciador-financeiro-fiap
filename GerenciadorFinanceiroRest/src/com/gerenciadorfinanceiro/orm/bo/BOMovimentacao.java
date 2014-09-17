/**
 * 
 */
package com.gerenciadorfinanceiro.orm.bo;

import java.util.ArrayList;
import java.util.List;

import com.gerenciadorfinanceiro.abstratas.Classe;
import com.gerenciadorfinanceiro.excecoes.ErroNegocio;
import com.gerenciadorfinanceiro.excecoes.SysErr;
import com.gerenciadorfinanceiro.orm.dao.DAOMovimentacao;
import com.gerenciadorfinanceiro.orm.dao.FinderMovimentacao;
import com.gerenciadorfinanceiro.orm.model.EnumUsuarioAutenticado;
import com.gerenciadorfinanceiro.orm.model.Movimentacao;
import com.gerenciadorfinanceiro.orm.model.usuario.Usuario;

/**
 * @author Alex
 *
 */
public class BOMovimentacao extends Classe  {

	private static BOMovimentacao instancia;
	private DAOMovimentacao dao;
	
	private BOMovimentacao(){}
	
	public List<Movimentacao> listarMovimentacoes(){
		log("Listando "+getNomeEntidade()+"s");
		List<Movimentacao> contatos = new ArrayList<Movimentacao>();
		
		contatos = FinderMovimentacao.getInstancia().listar();
		
		return contatos;
	}
	
	public Movimentacao inserir(Movimentacao movimentacao, String login) throws SysErr {
		Usuario usuario = BOUsuario.getInstancia().pesquisarPorLogin(login);
		if (existe(usuario)) {
			movimentacao.setUsuario(usuario);
			usuario.addMovimentacao(movimentacao);
			BOUsuario.getInstancia().alterar(usuario);
			return movimentacao;
		} else {
			throw new ErroNegocio(EnumUsuarioAutenticado.USUARIO_INEXISTENTE.getMensagem());
		}
	}
	
	public void atualizar(Movimentacao movimentacao) throws SysErr {
		getDao().atualizar(movimentacao);
	}

	/**
	 * Este método tem a inteligencia de verificar se o
	 * Contato a ser persistido já existe. Caso positivo,
	 * o contato é apenas atualizado 
	 */
	public List<Movimentacao> gravar(List<Movimentacao> movimentacoes) throws SysErr {
		for (Movimentacao o : movimentacoes) {
			if(o.getCodigo() !=null && FinderMovimentacao.getInstancia().find(o.getCodigo()) != null){
				getDao().atualizar(o);
				log(getNomeEntidade()+" salvo(a) com sucesso!");
			} else {
				getDao().inserir(o);
			}	
		}
		return movimentacoes;
	}


	public void excluir(Movimentacao o) throws SysErr {
		getDao().excluir(o);
	}

	/**
	 * @return the dao
	 */
	public final DAOMovimentacao getDao() {
		if(naoExiste(this.dao)){
			setDao(DAOMovimentacao.getInstancia());
		}
		return dao;
	}

	/**
	 * @param dao the dao to set
	 */
	public final void setDao(DAOMovimentacao dao) {
		this.dao = dao;
	}

	public static BOMovimentacao getInstancia() {
		if(instancia == null){
			instancia = new BOMovimentacao();
		}
		return instancia;
	}

	private String getNomeEntidade(){
		return CLASSE_NOME.substring(2);
	}
}
