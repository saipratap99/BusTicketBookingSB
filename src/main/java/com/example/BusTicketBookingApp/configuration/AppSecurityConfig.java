package com.example.BusTicketBookingApp.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.BusTicketBookingApp.filters.JwtRequestFilter;
import com.example.BusTicketBookingApp.filters.SessionRequestFilter;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	JwtRequestFilter jwtRequestFilter;
	
	@Autowired
	SessionRequestFilter sessionRequestFilter;
	
	// Authentication
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	// Authorization
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable()
			.authorizeHttpRequests()
			.antMatchers("/authenticate","/users/*")
			.permitAll()
			.anyRequest()
			.authenticated()
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
		
		
		http.exceptionHandling().authenticationEntryPoint((request, response, authException)->{	
			response.sendRedirect("/users/login?msg=Please+login&status=danger&show=show");
		});
		
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		http.addFilterBefore(sessionRequestFilter, UsernamePasswordAuthenticationFilter.class);

	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
}
