package com.example.BusTicketBookingApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UsersController {
	
	@GetMapping("/new")
	public String newUser() {
		System.out.println("new");
		return "/users/new.jsp";
	}
	
	@PostMapping("/create")
	public String createUser() {
		return "User created";
	}
}
