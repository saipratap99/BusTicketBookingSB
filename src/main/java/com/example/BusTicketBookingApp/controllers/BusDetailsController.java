package com.example.BusTicketBookingApp.controllers;

import java.security.Principal;
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
import com.example.BusTicketBookingApp.daos.UserRepo;
import com.example.BusTicketBookingApp.models.BusDetails;
import com.example.BusTicketBookingApp.models.Location;
import com.example.BusTicketBookingApp.models.ServiceDetails;
import com.example.BusTicketBookingApp.models.User;

@Controller
@RequestMapping("/bus_details")
public class BusDetailsController {
	
	@Autowired
	LocationRepo locationRepo;
	
	@Autowired
	ServiceDetailsRepo serviceDetailsRepo;
	
	@Autowired
	BusDetailsRepo busDetailsRepo;
	
	@Autowired
	UserRepo userRepo;
	
	@GetMapping("/new")
	public String newBusDetails(Model model) {
//		ModelAndView newBusDetailsMv = new ModelAndView("/bus_details/new.jsp");
		
		
//		List<String> services = serviceDetailsRepo.findAllProjectedByServiceName();
//		model.addAttribute("services", services);
		
		return "/bus_details/new.jsp";
	}
	
	@PostMapping("/create")
	public String create(BusDetails busDetails, Model model, Principal principal) {
		
		User user = userRepo.findByEmail(principal.getName());
		
		if(user != null && user.getRole().equals("ROLE_OPERATOR")) {
			busDetails.setOperator(user);
			busDetails.generateBusName();
			busDetailsRepo.save(busDetails);
		}
		
		
		return "/bus_details/new.jsp";
	}
}
