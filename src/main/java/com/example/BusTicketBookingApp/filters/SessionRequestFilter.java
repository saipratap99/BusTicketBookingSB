package com.example.BusTicketBookingApp.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.BusTicketBookingApp.utils.PropertiesUtil;

@Component
public class SessionRequestFilter extends OncePerRequestFilter{

	@Autowired
	PropertiesUtil propertiesUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		if(propertiesUtil.isSessionsBasedAuth()) {
			HttpSession session = request.getSession(false);
			if(session != null) {
				request.changeSessionId();
			}
		}
		
		filterChain.doFilter(request, response);
	}

}
