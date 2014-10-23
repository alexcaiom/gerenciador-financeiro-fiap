package com.organizadorfinanceiro.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import com.organizadorfinanceiro.abstratas.Classe;
import com.j256.ormlite.field.DatabaseField;

/**
 * Esta classe realiza a leitura do Bean passado
 * e cria as queries de criação de entidade baseado
 * nos atributos dela.
 * @author Alex
 *
 */
public class GeradorSQLBean extends Classe {
	
	private static final String CREATE_TABLE_SIMPLES = "create table ";
	private static final String DROP_TABLE_SIMPLES = "drop table ";
	private static final String INSERT_INTO_SIMPLES = "insert into ";
	private static final String UPDATE_SIMPLES = "update ";
	private static final String DELETE_FROM_SIMPLES = "delete from ";
	private static final String SELECT_FROM_SIMPLES = "select from ";
	private final Class objeto;
	
	private final List<Field> camposPK = new ArrayList<Field>();
	
	GeradorSQLBean(Class objeto){
		this.objeto = objeto;
		if(this.objeto != null){
			for(Field f: objeto.getDeclaredFields()){
				if(getCampo(f).contains("primary key")){
					camposPK.add(f);
				}
			}
		}
	}
	
	public String getCreateTable() {
		//Iniciando o comando
		StringBuilder comando = getComandoInicial(CREATE_TABLE_SIMPLES).append(" (");
		
		//Colocando os nomes dos campos
//		System.out.println("Campos: ");
		Field[] campos = objeto.getDeclaredFields();
		List<Field> fks = new ArrayList<Field>();
		
		/**
		 * 1° Parte - Cuidamos dos campos
		 */
		int quantidadeDeCampos = 0;
		for(int cont=0; cont < campos.length; cont++){
			
			Field campo = campos[cont];
			Annotation[] anotacoes = campo.getAnnotations();
			if(!getCampo(campo).contains("serialVersionUID") && !getCampo(campo).trim().isEmpty()){
				String nomeCampo = campo.getName();
				String tipoCampo = campo.getType().getName();
//				System.out.println(nomeCampo + " (" + campo.getType().getSimpleName() + ")");
				
				StringBuilder linhaCampo = new StringBuilder();
				
				linhaCampo.append(getCampo(campo));
				
				if(!linhaCampo.toString().trim().isEmpty()){
					if(quantidadeDeCampos > 0){
						comando.append(", \n");
					} else{
						comando.append(" \n");
					}
					comando.append(linhaCampo.toString());
				}
				
				/** Verificamos se o tipo do campo começa com 'com',
				 * um indicativo de que estamos lidando com uma chave
				 * Estrangeira (FK)
				 */
				if(tipoCampo.startsWith("com")){
					fks.add(campo);
				}
				
				quantidadeDeCampos++;
			}
		}
		
//		System.out.println();
		/**
		 * 2ª Parte: Agora lidamos com as FKs
		 */
		/*for(Field f: fks){
			comando.append(",\n").append(getChaveEstrangeira(f));
		}*/
		
		comando.append(")");
		
//		System.out.println(comando);
		
		
		return comando.toString();
	}

	/**
	 * @param comando
	 * @param fks
	 * @param campo
	 * @param anotacoes
	 * @param tipoCampo
	 */
	private String getCampo(Field campo){
		StringBuilder txtcampo = new StringBuilder();
		String tipoCampo = campo.getType().getName();
		
		/** Verificamos se o tipo do campo começa com 'com',
		 * um indicativo de que estamos lidando com uma chave
		 * Estrangeira (FK)
		 */
		if(!campo.getName().contains("serialVersionUID")){
			if((!tipoCampo.startsWith("com") || tipoCampo.startsWith("java.util") || 
					campo.getAnnotations().length == 0) || campo.getType().isPrimitive()){
				txtcampo.append(getCampoNormal(campo).trim()).append(" ").append(getTipoCampo(campo).trim()).append(" ").append(getAtributosDeAnotacoes(campo.getAnnotations()).trim());
			}else {
				txtcampo.append(getCampoChaveEstrangeira(campo).trim()).append(" ").append(getTipoCampo(campo).trim()).append(" ").append(getAtributosDeAnotacoes(campo.getAnnotations()).trim());
			}
		}
		
		return txtcampo.toString();
	}
	
	private String getCampoNormal(Field campo){
		/**
		 * No caso de Chave Estrangeira, fazemos a extração do ID
		 * do tipo informado
		 */
		StringBuilder txtcampo = new StringBuilder();
		txtcampo.append(campo.getName());
		return txtcampo.toString();
	}
	private String getCampoChaveEstrangeira(Field campo){
		/**
		 * No caso de Chave Estrangeira, fazemos a extração do ID
		 * do tipo informado
		 */
		StringBuilder txtcampo = new StringBuilder();
		if(!(campo.getType().isInterface() && (campo.getType().getSimpleName().equalsIgnoreCase("List") || 
				campo.getType().getSimpleName().equalsIgnoreCase("List")))){
			txtcampo.append(campo.getName()).append("_id ");
		}
		return txtcampo.toString();
	}
	
	/**
	 * Campo que interpreta a chave estrangeira,
	 * construindo a constraint
	 * @param campo
	 * @return
	 */
	private String getChaveEstrangeira(Field campo){
		if(campo != null){
			String nomeCampo = campo.getName();
			
			StringBuilder constraint = new StringBuilder();
			/**
			 * Se o nosso campo tiver implementado uma Interface com Generics,
			 * detectamos aqui e já extraimos o tipo do campo.
			 */
			
			String tipoCampo = campo.getType().getSimpleName();

			constraint.append("constraint fk_").append(tipoCampo).append("_").append(campo.getDeclaringClass().getSimpleName());
			constraint.append(" (").append(nomeCampo).append(") references ").append("tb_").append(tipoCampo);
			constraint.append(" (id)");

			return constraint.toString();
		}
		return "";
	}
	
	/**
	 * Metodo que transforma os tipos de dados
	 * do Java em tipos de dado de Banco
	 * @param campo
	 * @return
	 */
	private String getTipoCampo(Field campo) {
		Class classeTipo = null;
		if(!campo.getType().isPrimitive()){
			classeTipo = campo.getType();
			if(classeTipo == String.class){
				return "text";
			} else if(classeTipo == Long.class || classeTipo == Boolean.class || classeTipo == Integer.class){
				return "INTEGER";
			} else if(classeTipo.getName().contains("com.")){
				return "INTEGER";
			} else if (classeTipo == Calendar.class) {
				return "long";
			} else if (classeTipo == BigDecimal.class) {
				return "double";
			}
		} else {
			if(!campo.getName().contains("serialVersionUID")){
				if(campo.getType().getName().equalsIgnoreCase("long") ||
						campo.getType().getName().equalsIgnoreCase("boolean") ||
						campo.getType().getName().equalsIgnoreCase("int")){
					return "INTEGER";
				}
			}
		}
		return "";
	}
	
	/**
	 * Caso o Bean tenha anotações, estas são interpretadas
	 * para atributos de coluna no banco de dados.
	 * @param anotacoes
	 * @return
	 */
	private String getAtributosDeAnotacoes(Annotation[] anotacoes) {
		StringBuilder sb = new StringBuilder();
		String pk = "";
		StringBuilder nullable = new StringBuilder("");
		String auto_incremento = "";
		String unique = "";
		
		if(anotacoes != null && anotacoes.length > 0){
			for(Annotation anotacao: anotacoes){
				if(anotacao instanceof DatabaseField){
					DatabaseField a = (DatabaseField) anotacao;
					if(a.id()){
						pk = " primary key";
					}
					if(!a.canBeNull()){
						nullable.insert(0, " not null");
					}
					/*if(a.generatedId()){
						auto_incremento =" autoincrement";
					}*/
					if(a.unique()){
						unique = " unique";
					}
				}
			}
		}
		
		sb.append(pk).append(nullable.toString()).append(auto_incremento).append(unique);
		
		return sb.toString();
	}
	
	public String getDropTable(){
		return getComandoInicial(DROP_TABLE_SIMPLES).toString();
	}
	
	/**
	 * @return
	 */
	public String getNomeTabela() {
		return "tb_"+objeto.getSimpleName().toLowerCase(new Locale("pt", "BR"));
	}
	
	private StringBuilder getComandoInicial(String qualComando){
		StringBuilder sb = new StringBuilder(qualComando);
		sb.append(getNomeTabela());
		return sb;
	}
	
	public static GeradorSQLBean getInstancia(Class tipo){
		return new GeradorSQLBean(tipo);
	}
	
	public static void main(String[] args) {
//		System.out.println(GeradorSQLBean.getInstancia(Usuario.class).getCreateTable());
//		System.out.println(GeradorSQLBean.getInstancia(Usuario.class).getDropTable());
//		System.out.println(GeradorSQLBean.getInstancia(Usuario.class).getInsert());
	}

}
