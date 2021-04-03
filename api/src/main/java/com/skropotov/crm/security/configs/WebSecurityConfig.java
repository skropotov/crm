package com.skropotov.crm.security.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.skropotov.crm.security.filters.TokenFilter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	TokenFilter tokenFilter;
	
	@Autowired
	AuthenticationProvider authenticationProvider;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.addFilterBefore(tokenFilter, BasicAuthenticationFilter.class)
			.antMatcher("/**")
			.authenticationProvider(authenticationProvider)
			.authorizeRequests()
			.antMatchers("/users/**").hasAnyAuthority("ADMIN")
			.antMatchers("/clients/**").hasAnyAuthority("USER", "ADMIN")
			.antMatchers("/login").permitAll()
			.antMatchers("/current").permitAll();
		
		http.csrf().disable(); 
	}
	
}
