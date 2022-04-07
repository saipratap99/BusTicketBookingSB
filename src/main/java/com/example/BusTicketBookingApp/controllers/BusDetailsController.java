package com.example.BusTicketBookingApp.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.BusTicketBookingApp.daos.BusDetailsRepo;
import com.example.BusTicketBookingApp.daos.LocationRepo;
import com.example.BusTicketBookingApp.daos.ServiceDetailsRepo;
import com.example.BusTicketBookingApp.models.BusDetails;
import com.example.BusTicketBookingApp.models.Location;
import com.example.BusTicketBookingApp.models.ServiceDetails;

@Controller
@RequestMapping("/bus_details")
public class BusDetailsController {
	
	@Autowired
	LocationRepo locationRepo;
	
	@Autowired
	ServiceDetailsRepo serviceDetailsRepo;
	
	@Autowired
	BusDetailsRepo busDetailsRepo;
	
	@GetMapping("/new")
	public String newBusDetails(Model model) {
		ModelAndView newBusDetailsMv = new ModelAndView("/bus_details/new.jsp");
		
		List<String> locations = locationRepo.findAllProjectedByLocationName(); 

		List<String> services = serviceDetailsRepo.findAllProjectedByServiceName();

		model.addAttribute("locations", locations);
		model.addAttribute("services", services);
		
		return "/bus_details/new.jsp";
	}
	
	@PostMapping("/create")
	public String create(BusDetails busDetails,String location, String service, Model model) {
		
		Optional<Location> currLocation = locationRepo.findByLocationName(location);
		Optional<ServiceDetails> serviceDetails = serviceDetailsRepo.findByServiceName(service); 
		
		if(currLocation.isPresent() && serviceDetails.isPresent()) {
			busDetails.setCurrLocation(currLocation.get());
			busDetails.setServiceDetails(serviceDetails.get());
			busDetails.generateBusName();
			busDetailsRepo.save(busDetails);
		}
		
		
		return "/bus_details/new.jsp";
	}
}
