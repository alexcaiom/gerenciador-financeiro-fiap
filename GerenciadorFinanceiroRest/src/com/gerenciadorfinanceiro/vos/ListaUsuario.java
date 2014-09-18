/**
 * 
 */
package com.gerenciadorfinanceiro.vos;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.gerenciadorfinanceiro.orm.model.usuario.Usuario;

/**
 * @author Alex
 *
 */
@XmlRootElement(name="lista")
public class ListaUsuario extends ArrayList<Usuario> {

	public ListaUsuario() {}
	public ListaUsuario(List<Usuario> usuarios){
		super(usuarios);
	}
	
}
