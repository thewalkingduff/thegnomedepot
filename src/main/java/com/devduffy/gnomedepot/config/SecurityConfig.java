package com.devduffy.gnomedepot.config;

import org.springframework.boot.autoconfigure.security.reactive.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{		
	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http
			.csrf().disable()
	        .authorizeRequests()
	        	.antMatchers("/pub/**", "/user/**", "/product/search", "/product/details", "/", "/index", "/error/**", "/static/**", "/templates/**",
				 "/fragments/**", "/img/**", "/js/**", "/fonts/**", "/css/**").permitAll()
				.anyRequest().authenticated()
	        	.and()
	        .formLogin()
	            .loginPage("/user/login")
	            .loginProcessingUrl("/user/loginpost")
	            .defaultSuccessUrl("/")
	            .and()
	        .logout()
	            .invalidateHttpSession(true)
	            .logoutUrl("/user/logout")
	            .logoutSuccessUrl("/");
	}


    @Bean(name="passwordEncoder")
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}