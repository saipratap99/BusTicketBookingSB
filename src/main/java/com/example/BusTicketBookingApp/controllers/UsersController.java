package com.example.BusTicketBookingApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.BusTicketBookingApp.daos.UserRepo;
import com.example.BusTicketBookingApp.models.User;

@Controller
@RequestMapping("/users")
public class UsersController {
	
	
	@Autowired
	UserRepo userRepo;
	
	@GetMapping("/new")
	public ModelAndView newUser(String msg) {
		ModelAndView newUserMV = new ModelAndView("/users/new.jsp");
		newUserMV.addObject("msg", msg);
		return newUserMV;
	}
	
	@PostMapping("/create")
	public ModelAndView createUser(@RequestParam String firstName, 
							   @RequestParam String lastName,
							   @RequestParam String email,
							   @RequestParam String password,
							   @RequestParam String confirmPassword) {
		User user = new User(firstName, lastName, email, password);
		userRepo.save(user);
		return newUser("User " + firstName + " has been added!");
	}
}
