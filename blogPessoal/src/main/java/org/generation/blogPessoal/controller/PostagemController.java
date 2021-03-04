package org.generation.blogPessoal.controller;

import java.util.List;

import org.generation.blogPessoal.model.Postagem;
import org.generation.blogPessoal.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*Indicando que essa classe é um controller*/
@RestController
/*Definir a URI que ela sera acessada que sera o postagens*/
@RequestMapping("/postagens")
/*O front end pode vir de uma origem diferente
 *  e independente isso a nossa API tem que aceitar usando o CrossOringir
 *  usando o "*" ele pode aceitar de qualquer origem*/
@CrossOrigin("*")
public class PostagemController {
	/* Inserindo o repositorio dentro desse Controller*/
	
	/* Para instanciar usaremos a injeção de dependencia*/
	@Autowired
	private PostagemRepository repository;
	
	/*Mértodo FindAll
	 * Retorna uma lista do tipo Postagem*/
	@GetMapping /*Para requisições externas*/
	public ResponseEntity<List<Postagem>> GetAll(){
		/*Resposta HTTP*/
		return ResponseEntity.ok(repository.findAll());
	}
	
	/*Busca por ID com metodo Get
	 * esperando o parametro id*/
	@GetMapping("/{id}")
	/*Para o metodo conseguir capturar o valor precisaremos
	 *  do @PathVariable */
	public ResponseEntity<Postagem> GetById(@PathVariable long id){
		/*Por padrão ele vem tanto populado quanto nulo*/
		return repository.findById(id)
				/*Assim que for feito uma requisição @GetMapping em postagem para id
				 *  sera acessado esse metodo e ele ira capturar a variavel e retornar a interface com @Autowired
				 *  e o metodo findById vai te vai devolver tanto um objeto do tipo postagem
				 *   quanto um notFound caso tenha algum erro ou não exista*/
				.map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
		
	}
	/*Fazer uma busca por titulo
	 * usando um subcaminho que se chama titulo*/
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> GetByTitulo(@PathVariable String titulo){
		/*Recebendo o metodo repository*/
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	@PostMapping
	/*O nome do metodo é post*/
	public ResponseEntity<Postagem> post (@RequestBody Postagem postagem){
		/*Salvando na base de dados*/
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));
	}
	
	@PutMapping
	/*O nome do metodo é put*/
	public ResponseEntity<Postagem> put (@RequestBody Postagem postagem){
		/*Chamando o mesmo metodo para atualização*/
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(postagem));
	}
	
	/*Ele é parecido com o GetMapping quando passamos um parametro id*/
	@DeleteMapping("/{id}")
	/*void pois não retorna nada, so status ok por ter sido deletado*/
	public void delete(@PathVariable long id) {
		/*Não precisa de return*/
		repository.deleteById(id);
	}
	
	
}
