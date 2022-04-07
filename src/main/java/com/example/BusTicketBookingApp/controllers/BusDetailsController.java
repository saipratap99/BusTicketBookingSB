package com.example.BusTicketBookingApp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.BusTicketBookingApp.daos.LocationRepo;
import com.example.BusTicketBookingApp.models.Location;

@Controller
@RequestMapping("/bus_details")
public class BusDetailsController {
	
	@Autowired
	LocationRepo locationRepo;
	
	@GetMapping("/new")
	public ModelAndView newBusDetails() {
		ModelAndView newBusDetailsMv = new ModelAndView("/bus_details/new.jsp");
		
		List<Location> locations = locationRepo.findAll();
		
		newBusDetailsMv.addObject("locations", locations);
		
		return newBusDetailsMv;
	}
}