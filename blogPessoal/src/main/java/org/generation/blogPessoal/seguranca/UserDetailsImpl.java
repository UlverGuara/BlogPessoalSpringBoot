package org.generation.blogPessoal.seguranca;

import java.util.Collection;

import org.generation.blogPessoal.model.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/*implements para criar uma regra de negocios da interface UserDetails*/
public class UserDetailsImpl implements UserDetails{

	/*Classe para controle interno*/
	private static final long serialVersionUID = 1L;
	
	/*Atributos privados*/
	private String userName;
	private String password;
	
	/*Construtor de classe*/
	public UserDetailsImpl(Usuario user) {
		this.userName = user.getUsuario();
		this.password = user.getSenha();
	}
	
	/*Construtor vazio*/
	public UserDetailsImpl() {}
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		/*colocando o password no retorno*/
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		/*colocando o userName no retorno*/
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		/*Mudando para true como boa pratica*/
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		/*Mudando para true como boa pratica*/
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		/*Mudando para true como boa pratica*/
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		/*Mudando para true como boa pratica*/
		return true;
	}

}
