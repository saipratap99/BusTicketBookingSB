package com.example.BusTicketBookingApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/bus_details")
public class BusDetailsController {
	
	@GetMapping("/new")
	public ModelAndView newBusDetails() {
		ModelAndView newBusDetailsMv = new ModelAndView("/bus_details/new.jsp");
		return newBusDetailsMv;
	}
}
