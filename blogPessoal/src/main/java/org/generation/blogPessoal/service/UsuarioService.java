package org.generation.blogPessoal.service;

import java.nio.charset.Charset;
import java.util.Optional;
import org.apache.commons.codec.binary.Base64;

import org.generation.blogPessoal.model.UserLogin;
import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/*Essa classe se trata de um serviço*/
@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
	
	/*Retornando um metodo de nome CadastrarUsuario*/
	public Usuario CadastrarUsuario(Usuario usuario) {
		/*Instanciando um objeto de nome encoder*/
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		/*Variavel do tipo String para receber a senha*/
		String senhaEncoder = encoder.encode(usuario.getSenha());
		/*Acessando e modificando o atributo senha e passando a senha encriptada*/
		usuario.setSenha(senhaEncoder);
		
		return repository.save(usuario);
		
	}
	
	/*Regra de negocio que vai ditar tudo que se refere a logar
	 * usando o UserLogin*/
	public Optional<UserLogin> Logar(Optional<UserLogin> user){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		/*Criando objeto do tipo Optional*/
		Optional<Usuario> usuario = repository.findByUsuario(user.get().getUsuario());
		
		/*Condição se o objeto usuario estiver presente
		 *  eu vou comparar a senha encriptada com a senha que o usuario digitou*/
		if(usuario.isPresent()) {
			/*O encoder vai pegar duas senhas,
			 * uma encriptada e outra não e ele vai verificar se é igual,
			 * se for igual ele vai entrar nesse if*/
			if(encoder.matches(user.get().getSenha(), usuario.get().getSenha())) {
				
				/*Regra de negocio para devolver a senha encriptada,
				 * concatenando duas informações:
				 * O usuario e a senha com : entre elas*/
				String auth = user.get().getUsuario() + ":" + user.get().getSenha();
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				/*Autenticação Header
				 * dentro dela vai estar o prefixo basic*/
				String authHeader = "Basic " + new String(encodedAuth);
				
				user.get().setToken(authHeader);
				user.get().setNome(usuario.get().getNome());
				
				return user;
			}
		}
		/*Caso ele não entre no if
		 * é entendido que esse usuario não existe na nossa base de dados*/
		return null;
	}

}
