package com.example.BusTicketBookingApp.controllers;

import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.BusTicketBookingApp.daos.UserRepo;
import com.example.BusTicketBookingApp.models.User;
import com.example.BusTicketBookingApp.services.CookieService;
import com.example.BusTicketBookingApp.services.UserService;
import com.example.BusTicketBookingApp.utils.BasicUtil;
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
	
	@Autowired
	BasicUtil basicUtil;
	
	@GetMapping("/new")
	public ModelAndView newUser(@ModelAttribute("msg") String msg, @ModelAttribute("show") String show, @ModelAttribute("status") String status, Principal principal, HttpServletResponse response) throws IOException {
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
	public String createUser(@Valid User user,
							   BindingResult result,
							   HttpServletResponse response,
							   Principal principal, 
							   RedirectAttributes redirectAttributes) throws IOException {

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
				user.setRole("ROLE_USER");
				userService.save(user);
				msg += "User " + user.getFirstName() + " has been added!";
				status = "success";
			}
		}
		
		redirectAttributes.addFlashAttribute("msg", msg);
		redirectAttributes.addFlashAttribute("status", status);
		redirectAttributes.addFlashAttribute("show", "show");
		
		return "redirect:/users/new";
	}
	
	@GetMapping("/login")
	public ModelAndView newLogin(@ModelAttribute("msg") String msg,@ModelAttribute("status") String status,@ModelAttribute("show") String show, HttpServletResponse response, Principal principal) throws IOException {
		ModelAndView newUserLoginMV = new ModelAndView("/users/login.jsp");

		Optional<User> user = basicUtil.getUser(principal); 
		
		if(user.isPresent())
			response.sendRedirect("/");
		
		newUserLoginMV.addObject("msg", msg);
		newUserLoginMV.addObject("show", show);
		newUserLoginMV.addObject("status", status);
		
		return newUserLoginMV;
	}
	
	@PostMapping("/login")
	public String authenticate(@RequestParam String email,
									@RequestParam String password,
									RedirectAttributes redirectAttributes,
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
			redirectAttributes.addFlashAttribute("msg", "Invalid email or password");
			redirectAttributes.addFlashAttribute("status","danger");
			redirectAttributes.addFlashAttribute("show","show");
			
			return "redirect:/users/login";
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
