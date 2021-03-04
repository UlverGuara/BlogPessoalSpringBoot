package org.generation.blogPessoal.repository;

import java.util.List;

import org.generation.blogPessoal.model.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*Uma classe de repositorio*/
@Repository
/* Herdado de outra interface JPA*/
public interface PostagemRepository extends JpaRepository<Postagem, Long> {
	/* Métodos query para fazermos consultas personalizadas
	 * findAllByTitulo - ele busca todos pelo titulo na entidade titulo no Postagem.java
	 * Containing - tudo que conter as caracteres dentro da variavel titulo
	 * IgnoreCase - ele não leva em consideração palavras em maiusculos e minusculos*/
	public List<Postagem> findAllByTituloContainingIgnoreCase (String titulo);
	

}
