package com.example.BusTicketBookingApp.controllers;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.view.RedirectView;

import com.example.BusTicketBookingApp.daos.UserRepo;
import com.example.BusTicketBookingApp.models.AuthenticationResponse;
import com.example.BusTicketBookingApp.models.User;
import com.example.BusTicketBookingApp.services.CookieService;
import com.example.BusTicketBookingApp.services.UserService;
import com.example.BusTicketBookingApp.utils.JwtUtil;

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
	
	@PostMapping("/create")
	public ModelAndView createUser(@RequestParam String firstName, 
							   @RequestParam String lastName,
							   @RequestParam String email,
							   @RequestParam String password,
							   @RequestParam String confirmPassword, 
							   HttpServletResponse response,
							   Principal principal) throws IOException {
		
		User user = new User(firstName, lastName, email, password);
		
		String msg = "", status = "danger";
		
		if(!firstName.matches("[a-zA-Z]{2,}"))
			msg += "First name must be length of 2 and it should contains only Alpabets.</br>";
		
		else if(!email.matches("[a-zA-Z0-9]+[@]{1}[a-zA-Z]+[.][a-zA-Z]{2,3}"))
			msg += "Email must be valid.</br>";
		
		else if(userRepo.existsByEmail(email))
			msg += "Email already exsits.</br>";
		
		else if(!password.equals(confirmPassword))
			msg += "Passwords should match.</br>";
		
		else {
			userService.save(user);
			msg += "User " + firstName + " has been added!";
			status = "success";
		}
		
		return newUser(msg, "show", status,principal, response);
	}
	
	@GetMapping("/login")
	public ModelAndView newLogin(String msg, String status, String show, HttpSession session) {
		ModelAndView newUserLoginMV = new ModelAndView("/users/login.jsp");
		
		newUserLoginMV.addObject("msg", msg);
		newUserLoginMV.addObject("show", show);
		newUserLoginMV.addObject("status", status);
		
		return newUserLoginMV;
	}
	
	@PostMapping("/login")
	public String authenticate(@RequestParam String email,
									@RequestParam String password,
									HttpServletResponse resp, HttpServletRequest request) {
		
		
		try {
			
			// authenticate if credentials are matching
			authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(email, password)	
			);
			
			// if matched gets the UserDetails object 
			UserDetails userDetails = userDetailsService.loadUserByUsername(email);

//			String jwt = jwtUtil.generateToken(userDetails);
//			resp.addCookie(cookieService.getJwtCookie(jwt));
			
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
		return logOutModelAndView;
	}
	
	@PostMapping("/logout")
	public String deleteSession(HttpSession session, HttpServletRequest request) {
		SecurityContextHolder.getContext().setAuthentication(null);
		return "redirect:/users/login?msg=User+logged+out&status=success&show=show";
	}
}
