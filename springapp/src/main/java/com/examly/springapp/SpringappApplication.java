package com.examly.springapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.examly.springapp.controller.JWTAuthorizationFilter;
import com.examly.springapp.service.LoginService;

@Configuration
@ComponentScan
@EnableJpaRepositories
@SpringBootApplication
@EnableAutoConfiguration
public class SpringappApplication {

	public static void main(String[] args){
		SpringApplication.run(SpringappApplication.class, args);
	}
	
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {
		@Autowired
		private JWTAuthorizationFilter jwtAuthorizationFilter;
		@Autowired
		private LoginService loginService;
		
		@Autowired
		private CustomAuthenticationEntryPoint entryPoint;
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.cors().and().csrf().disable() 
				.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/login").permitAll()
				.antMatchers(HttpMethod.POST, "/signup").permitAll()
				.antMatchers(HttpMethod.GET, "/files/**").permitAll()
				.anyRequest().authenticated()
				.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.exceptionHandling()
				.authenticationEntryPoint(entryPoint);
			http.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
		}
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(loginService);
		}
		@SuppressWarnings("deprecation")
		@Bean
		public PasswordEncoder generateEncoder() {
			return NoOpPasswordEncoder.getInstance();
		}
		@Bean
		public AuthenticationManager authManagerBean() throws Exception {
			return super.authenticationManagerBean();
		}
	}

}
