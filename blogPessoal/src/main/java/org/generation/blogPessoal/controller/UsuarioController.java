package org.generation.blogPessoal.controller;

import java.util.Optional;

import org.generation.blogPessoal.model.UserLogin;
import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*Vai indicar que essa classe se trata de um controller*/
@RestController
/* Vamos acessar atraves desse caminho */
@RequestMapping("/usuarios")
/*
 * Dentro com dois parametros um para origins(todas) allowedHeaders quer dizer
 * que dentro do headers também vamos aceitar qualquer informações
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

	/*
	 * Ao inves de injetar com o Autowired os repository nos vamos injetar a classe
	 * de services
	 */
	@Autowired
	private UsuarioService usuarioService;

	/*
	 * Tanto logar quando cadastrar são post lembrando que umas das regras de
	 * segurança é que o usuario não tem acesso ao login e a senha que esta sendo
	 * passado para a API se ele for de alguma outra forma com o get ele tera que
	 * passar pelo pathvariable e isso não é seguro
	 */
	@PostMapping("/logar")
	/*
	 * Metodo publico do tipo ResponseEntity Ele vai retornar um UserLogin Nos vamos
	 * receber pela body pelo RequestBody do tipo Optional(ou seja ele pode receber
	 * algo ou receber nada) E o nome do objeto é user
	 */
	public ResponseEntity<UserLogin> Autentication(@RequestBody Optional<UserLogin> user) {
		/* Expressão lambda */
		return usuarioService.Logar(user).map(resp -> ResponseEntity.ok(resp))
				/*
				 * Caso der errado ou diferente se o login não for feito ou feito de forma
				 * indevida ele tem que devolver um "Sem autorização"
				 */
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

	/* Metodo de Cadastrar */
	@PostMapping("/cadastrar")
	/*
	 * Recebendo como paramentro via body um objeto do tipo Usuario(não é mais o
	 * UserLogin, pois ele esta cadastrando)
	 */
	public ResponseEntity<Usuario> Post(@RequestBody Usuario usuario) {
		/* Chamando o metodo do repository */
		return ResponseEntity.status(HttpStatus.CREATED)
				/* Chamando o service */
				.body(usuarioService.CadastrarUsuario(usuario));

	}

}
