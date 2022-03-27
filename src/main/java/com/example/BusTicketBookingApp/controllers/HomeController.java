package com.example.BusTicketBookingApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	@GetMapping("/")
	public ModelAndView index() {
		ModelAndView indexView = new ModelAndView("/home/index.jsp");
		return indexView;
	}
}
