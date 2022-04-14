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
import com.example.BusTicketBookingApp.utils.BasicUtil;

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
	
	@Autowired
	BasicUtil basicUtil;
	
	@GetMapping("/new")
	public String newBusDetails(Principal principal, Model model) {
		basicUtil.addNavBarAttributesToModel(principal, model);
		return "/bus_details/new.jsp";
	}
	
	@PostMapping("/create")
	public String create(BusDetails busDetails, Model model, Principal principal) {
		
		Optional<User> user = basicUtil.getUser(principal);
		
		if(user.isPresent() && user.get().getRole().equals("ROLE_OPERATOR")) {
			busDetails.setOperator(user.get());
			busDetails.generateBusName();
			busDetailsRepo.save(busDetails);
		}
		
		return "redirect:/bus_details/new.jsp";
	}
}
