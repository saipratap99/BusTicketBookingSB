package com.example.BusTicketBookingApp.controllers;

import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

import javax.persistence.Basic;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.BusTicketBookingApp.daos.UserRepo;
import com.example.BusTicketBookingApp.models.AuthenticationRequest;
import com.example.BusTicketBookingApp.models.AuthenticationResponse;
import com.example.BusTicketBookingApp.models.User;
import com.example.BusTicketBookingApp.utils.BasicUtil;
import com.example.BusTicketBookingApp.utils.JwtUtil;

@Controller
public class HomeController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	BasicUtil basicUtil;
	
	@Autowired
	JwtUtil jwtUtil;
	
	@GetMapping("/")
	public String index(HttpServletRequest req, HttpSession session,Model model, Principal principal) throws IOException {	
		basicUtil.addNavBarAttributesToModel(principal, model);
		return "/home/index.jsp";
	}
	
	/*
	@PostMapping("/authenticate")
	public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest authRequest) throws Exception{
		try {
			authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())	
			);
		}catch(BadCredentialsException e) {
			throw new Exception("Invalid username or password", e);
		}
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
		String jwt = jwtUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
	
	*/
}
