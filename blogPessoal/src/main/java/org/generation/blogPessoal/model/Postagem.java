package org.generation.blogPessoal.model;
/* Verifique se o seu package esta com o nome correto..  */

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/* Essa anotação indica que essa classe é uma entidade do JPA hibernate*/
@Entity
/* Essa entidade sera uma tabela com um nome de "postagem"*/
public class Postagem {
	
	/* É o id das postagens*/
	@Id
	/* O id sera um valor gerado, no banco de dados ele sera uma PRIMARY KEY*/
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	/* O titulo não podera ser vazio*/
	@NotNull
	/* Determinar a quantidade de caracteres com o seu mínimo(min) e máximo(max)*/
	@Size(min = 5, max = 100)
	private String titulo;
	
	/* O titulo não podera ser vazio*/
	@NotNull
	/* Determinar a quantidade de caracteres com o seu mínimo(min) e máximo(max)*/
	@Size(min = 10, max = 500)
	private String texto;
	
	/* Indica para o JPA hibernate que estamos trabalhando com tempo
	 *  com o seu parametro com o tipo de tempo*/
	@Temporal(TemporalType.TIMESTAMP)
	private Date date =new java.sql.Date(System.currentTimeMillis());
	/* Método para calcular a data,
	 *  assim que passar um dado por essa classe ele sera capturado quando passou  */

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	
	
}
