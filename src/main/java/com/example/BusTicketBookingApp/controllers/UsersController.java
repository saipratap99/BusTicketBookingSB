package com.example.BusTicketBookingApp.controllers;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.BusTicketBookingApp.daos.UserRepo;
import com.example.BusTicketBookingApp.models.User;
import com.example.BusTicketBookingApp.services.CookieService;
import com.example.BusTicketBookingApp.services.UserService;
import com.example.BusTicketBookingApp.utils.JwtUtil;
import com.example.BusTicketBookingApp.utils.PropertiesUtil;

@Controller
@RequestMapping("/users")
public class UsersController {
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	JwtUtil jwtUtil;
	
	@Autowired
	UserService userService;
	
	@Autowired
	CookieService cookieService;
	
	@Autowired
	PropertiesUtil propertiesUtil;
	
	@GetMapping("/new")
	public ModelAndView newUser(String msg, String show, String status, Principal principal, HttpServletResponse response) throws IOException {
		if(principal != null)
			response.sendRedirect("/");
		
		ModelAndView newUserMV = new ModelAndView("/users/new.jsp");
	
		newUserMV.addObject("msg", msg);
		newUserMV.addObject("show", show);
		newUserMV.addObject("status", status);
		
		return newUserMV;
	}
	
	
	//	Binding result must come after Object to be validated
	@PostMapping("/create")
	public ModelAndView createUser(@Valid User user,
							   BindingResult result,
							   HttpServletResponse response,
							   Principal principal) throws IOException {

		String msg = "", status = "danger";
		
		if(result.hasErrors()) {
			for(FieldError error: result.getFieldErrors()) 
				msg += error.getField() + ": " + error.getDefaultMessage() + "<br>";
		}else {
			if(userRepo.findByEmail(user.getEmail()) != null)
				msg += "Email already exsits";
			else if(!user.getPassword().equals(user.getConfirmPassword()))
				msg += "Password must be same";
			else {
				userService.save(user);
				msg += "User " + user.getFirstName() + " has been added!";
				status = "success";
			}
		}
		
		return newUser(msg, "show", status, principal, response);
	}
	
	@GetMapping("/login")
	public ModelAndView newLogin(String msg, String status, String show, HttpServletResponse response, Principal principal) throws IOException {
		ModelAndView newUserLoginMV = new ModelAndView("/users/login.jsp");
		
		if(principal != null)
			response.sendRedirect("/");
		
		newUserLoginMV.addObject("msg", msg);
		newUserLoginMV.addObject("show", show);
		newUserLoginMV.addObject("status", status);
		newUserLoginMV.addObject("loggedIn", false);
		
		return newUserLoginMV;
	}
	
	@PostMapping("/login")
	public String authenticate(@RequestParam String email,
									@RequestParam String password,
									HttpServletResponse resp, HttpServletRequest request) throws UsernameNotFoundException {
		
		try {
			// authenticate if credentials are matching
			authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(email, password)	
			);
			
			// if matched gets the UserDetails object 
			UserDetails userDetails = userDetailsService.loadUserByUsername(email);
			
			if(propertiesUtil.isJWTBasedAuth())
				generateJWTAccessTokenAndStoreToCookie(userDetails, resp);
			

			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
					userDetails,
					null,
					userDetails.getAuthorities()
			);
			
			usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			
			return "redirect:/";
			
		}catch(BadCredentialsException e) {
			return "redirect:/users/login?msg=Invalid+email+or+password&status=danger&show=show";
		}
		
	}
	
	@GetMapping("/logout")
	public ModelAndView logOut() {
		ModelAndView logOutModelAndView = new ModelAndView("/users/logout.jsp");
		logOutModelAndView.addObject("loggedIn", true);
		return logOutModelAndView;
	}
	
	/*
	 
	@PostMapping("/logout")
	public String deleteSession(HttpSession session, HttpServletRequest request) {
		
		if(propertiesUtil.isSessionsBasedAuth())
			session.invalidate();
		if(propertiesUtil.isJWTBasedAuth()) {}
		
		SecurityContextHolder.getContext().setAuthentication(null);
		
		return "redirect:/users/login?msg=User+logged+out&status=success&show=show";
	}
	
	*/
	
	public void generateJWTAccessTokenAndStoreToCookie(UserDetails userDetails, HttpServletResponse response) {
		String jwt = jwtUtil.generateToken(userDetails);
		response.addCookie(cookieService.getJwtCookie(jwt));		
	}
	
}
