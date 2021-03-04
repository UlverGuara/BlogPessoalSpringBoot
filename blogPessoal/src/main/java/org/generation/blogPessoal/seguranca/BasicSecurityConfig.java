package org.generation.blogPessoal.seguranca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/*Abilitando a configuração de web security*/
@EnableWebSecurity
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	/*
	 * Injeção de dependencia dentro de uma classe do WebSecurityConfigurerAdapter
	 */
	private UserDetailsService userDetailsService;

	/*
	 * Metodo que vai sobreescrever o metodo de dentro do userDetailssService
	 * anotação Override deixa explícito que é uma subescrita de método
	 */
	@Override
	/*
	 * metodo protegido throw é uma tratativa de erros
	 */
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		/* Assim que o metodo for chama nos vamos mostrar o objeto auth */
		auth.userDetailsService(userDetailsService);
	}

	/*
	 * Bean é uma classe aonde o programar define que vai gerenciar as instancias
	 * dessa classe
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
				/*
				 * Essa configuração serve para liberar alguns caminhos dentro do meu controller
				 * para que o cliente tenha acesso sem usar token para ele logar ele tem que ter
				 * acesso dentro desses endpoint
				 */
				.antMatchers("/usuarios/logar").permitAll().antMatchers("/usuarios/cadastrar").permitAll()
				/* Todas as outras requisições deverão ser autenticadas */
				.anyRequest().authenticated().and().httpBasic()
				/*
				 * Vai indicar qual é o tipo de cessão que vamos utilizar essa sessão não sera
				 * guardada
				 */
				.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().cors().and()
				.csrf().disable();

	}

}
