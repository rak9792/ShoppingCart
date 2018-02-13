package com.packt.webstore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter 
{
	
	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.inMemoryAuthentication().withUser("rakshit").password("pa55word").roles("USER");
		
		auth.inMemoryAuthentication().withUser("admin").password("root123").roles("USER","ADMIN");
	}
	
	@Override
	protected void configure(HttpSecurity httpSecutrity) throws Exception
	{
		httpSecutrity.formLogin().loginPage("/login").usernameParameter("userId").passwordParameter("password");
		
		httpSecutrity.formLogin().defaultSuccessUrl("/market/products").failureUrl("/login?error");
		
		httpSecutrity.logout().logoutSuccessUrl("/login?logout");
		
		httpSecutrity.exceptionHandling().accessDeniedPage("/login?accessDenied");
		
		httpSecutrity.authorizeRequests().antMatchers("/").permitAll().antMatchers("/**/add").access("hasRole('ADMIN')").antMatchers("/**/market/**").access("hasRole('USER')").antMatchers("/customers/**").access("hasRole('ADMIN')");
		
		httpSecutrity.csrf().disable();
	}
}
