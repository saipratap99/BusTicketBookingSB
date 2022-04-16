package com.example.BusTicketBookingApp.controllers;

import java.security.Principal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("/")
	public String getBuses(Model model, Principal principal) {
		
		basicUtil.addNavBarAttributesToModel(principal, model);
		
		List<Map<String, String>> busesMap = new LinkedList<>();
		List<BusDetails> busDetails = busDetailsRepo.findAll();
	
		for(BusDetails busDetail: busDetails) {
			Map<String, String> busMap = new LinkedHashMap<>();

			busMap.put("Bus Id", String.valueOf(busDetail.getId()));
			busMap.put("Bus Name", busDetail.getBusName());
			busMap.put("Bus Reg Number", busDetail.getBusRegNumber());
			busMap.put("Bus Type", busDetail.getBusType());
			busMap.put("Seating Type", busDetail.getSeatingType());
			busMap.put("Seat Count", String.valueOf(busDetail.getSeatCount()));
			busMap.put("Operator", busDetail.getOperator().getOperator());
			busMap.put("Last Maintance", busDetail.getLastMaintance().toString());
			busMap.put("On Service", busDetail.getOnService().toString());
			busMap.put("Update", "/bus_details/" + busDetail.getId() + "/edit");
			busMap.put("Delete", "/bus_details/" + busDetail.getId());
			
			busesMap.add(busMap);
		}
		
		List<String> colHeaders = new LinkedList<>();
		if(!busesMap.isEmpty()) 
			colHeaders.addAll(busesMap.get(0).keySet());
		
		
		model.addAttribute("busesMap", busesMap);
		model.addAttribute("colHeaders", colHeaders);
		return "/bus_details/index.jsp";
	}
	
	@GetMapping("/{id}")
	public String showBusDetails(@PathVariable int id ,Model model, Principal principal) {
		
		basicUtil.addNavBarAttributesToModel(principal, model);
		
		Optional<BusDetails> busDetails = busDetailsRepo.findById(id);
		
		if(busDetails.isPresent())
			model.addAttribute("busDetails", busDetails.get());
		
		return "/bus_details/show.jsp";
	}
	
	
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
	
	@GetMapping("/{id}/edit")
	public String updateForm(@PathVariable int id,Model model, Principal principal) {
		basicUtil.addNavBarAttributesToModel(principal, model);
		
		Optional<BusDetails> busDetails = busDetailsRepo.findById(id);
		
		if(busDetails.isPresent())
			model.addAttribute("busDetails", busDetails.get());
		return "/bus_details/edit.jsp";
	}
	
	@PostMapping("/{id}")
	public String update(@PathVariable int id, BusDetails busDetails,Model model, Principal principal) {
		basicUtil.addNavBarAttributesToModel(principal, model);
		
		Optional<User> user = basicUtil.getUser(principal);
		Optional<BusDetails> existingBusDetails = busDetailsRepo.findById(id);
		
		if(user.isPresent() && user.get().getRole().equals("ROLE_OPERATOR") && existingBusDetails.isPresent() && existingBusDetails.get().getOperator().getOperator().equals(user.get().getOperator())) {
			busDetails.setId(id);
			busDetails.setOperator(user.get());
			busDetails.generateBusName();
			busDetailsRepo.save(busDetails);
		}
		
		return "redirect:/bus_details/";
	}
	
	
}
