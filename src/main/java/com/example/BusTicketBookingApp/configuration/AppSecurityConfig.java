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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.BusTicketBookingApp.filters.JwtRequestFilter;
import com.example.BusTicketBookingApp.filters.SessionRequestFilter;
import com.example.BusTicketBookingApp.utils.PropertiesUtil;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	JwtRequestFilter jwtRequestFilter;
	
	@Autowired
	SessionRequestFilter sessionRequestFilter;
	
	@Autowired
	PropertiesUtil propertiesUtil;
	
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
			.authenticated();
		
		if(propertiesUtil.isSessionsBasedAuth())
			http
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
		
		if(propertiesUtil.isJWTBasedAuth())
			http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.exceptionHandling().authenticationEntryPoint((request, response, authException)->{	
			response.sendRedirect("/users/login?msg=Please+login&status=danger&show=show");
		});
		
		
		if(propertiesUtil.isJWTBasedAuth()) {	
			http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		}
		if(propertiesUtil.isSessionsBasedAuth())
			http.addFilterBefore(sessionRequestFilter, UsernamePasswordAuthenticationFilter.class);

		http
		.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/users/logout", "POST"))
			.clearAuthentication(true)
			.deleteCookies("jwt","SESSION")
			.logoutSuccessUrl("/users/login?msg=User+logged+out&status=success&show=show");
		
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
