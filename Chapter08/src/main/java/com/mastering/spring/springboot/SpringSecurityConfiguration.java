package com.mastering.spring.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user1").password("{noop}user1-password").roles("ADMIN");
		auth.inMemoryAuthentication().withUser("user2").password("{noop}user2-password").roles("USER");
	}

	/*
	 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
	 * Exception { auth.inMemoryAuthentication().withUser("user3").password(
	 * "{noop}user3-password").roles("ADMIN");
	 * auth.inMemoryAuthentication().withUser("user4").password(
	 * "{noop}user4-password").roles("USER"); }
	 */

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//
//		http.authorizeRequests()
//			.anyRequest().authenticated()
//			.antMatchers("/admin/**").hasRole("ADMIN")
//			.antMatchers("/users/**").hasRole("USER")
//			.antMatchers("/login").permitAll();
//
//	}

}