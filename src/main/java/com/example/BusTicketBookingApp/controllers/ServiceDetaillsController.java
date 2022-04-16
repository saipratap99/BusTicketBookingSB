package com.example.BusTicketBookingApp.controllers;

import java.security.Principal;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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


import com.example.BusTicketBookingApp.daos.LocationRepo;
import com.example.BusTicketBookingApp.daos.ServiceDetailsRepo;
import com.example.BusTicketBookingApp.daos.UserRepo;
import com.example.BusTicketBookingApp.models.BusDetails;
import com.example.BusTicketBookingApp.models.Location;
import com.example.BusTicketBookingApp.models.ServiceDetails;
import com.example.BusTicketBookingApp.models.User;
import com.example.BusTicketBookingApp.projections.IdAndLocation;
import com.example.BusTicketBookingApp.utils.BasicUtil;

@Controller
@RequestMapping("/service_details")
public class ServiceDetaillsController {
	
	@Autowired
	LocationRepo locationRepo;
	
	@Autowired
	ServiceDetailsRepo serviceDetailsRepo;
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	BasicUtil basicUtil;
	
	@GetMapping("/")
	public String index(Model model, Principal principal) {
		basicUtil.addNavBarAttributesToModel(principal, model);
		
		List<Map<String, String>> servicesMap = new LinkedList<>();
		List<ServiceDetails> serviceDetails = serviceDetailsRepo.findAll();
	
		for(ServiceDetails serviceDetail: serviceDetails) {
			Map<String, String> serviceMap = new LinkedHashMap<>();
			
			serviceMap.put("Service Id", String.valueOf(serviceDetail.getId()));
			serviceMap.put("Service Number", serviceDetail.getServiceNumber());
			serviceMap.put("Service Name", serviceDetail.getServiceName());
			serviceMap.put("Service Type", serviceDetail.getServiceType());
			serviceMap.put("Departure Location", serviceDetail.getDepartureLocation().getLocationName());
			serviceMap.put("Arrival Location", serviceDetail.getArrivalLocation().getLocationName());
			serviceMap.put("Distance", String.valueOf(serviceDetail.getDistance()));
			serviceMap.put("Update", "/service_details/" + serviceDetail.getId() + "/edit");
			serviceMap.put("Delete", "/service_details/" + serviceDetail.getId());
			
			servicesMap.add(serviceMap);
		}
		
		List<String> colHeaders = new LinkedList<>();
		if(!servicesMap.isEmpty()) 
			colHeaders.addAll(servicesMap.get(0).keySet());
		
		model.addAttribute("servicesMap", servicesMap);
		model.addAttribute("colHeaders", colHeaders);
		
		return "/service_details/index.jsp";
	}
	
	@GetMapping("/new")
	public String newBusDetails(Model model, Principal principal) {
		List<String> locations = locationRepo.findAllProjectedByLocationName(); 

		model.addAttribute("locations", locations);
		basicUtil.addNavBarAttributesToModel(principal, model);
		
		return "/service_details/new.jsp";
	}
	
	@PostMapping("/create")
	public String create(ServiceDetails serviceDetail, String depLocation, String arrLocation, Model model, Principal principal) {
				
		Optional<Location> departureLocation = locationRepo.findByLocationName(depLocation);
		Optional<Location> arrivalLocation = locationRepo.findByLocationName(arrLocation);
		
		User user = userRepo.findByEmail(principal.getName());
		
		if(user != null && departureLocation.isPresent() && arrivalLocation.isPresent() && user.getRole().equals("ROLE_OPERATOR")) {
			serviceDetail.setDepartureLocation(departureLocation.get());
			serviceDetail.setArrivalLocation(arrivalLocation.get());
			serviceDetail.genrateServiceName();
			serviceDetailsRepo.save(serviceDetail);
		}
		
		return "redirect:/service_details/new";
	}
	
	@GetMapping("/{id}/edit")
	public String updateForm(@PathVariable int id ,Model model, Principal principal) {
		
		Optional<ServiceDetails> serviceDetails = serviceDetailsRepo.findById(id);
		List<String> locations = locationRepo.findAllProjectedByLocationName(); 
		basicUtil.addNavBarAttributesToModel(principal, model);
		
		model.addAttribute("locations", locations);

		if(serviceDetails.isPresent())
			model.addAttribute("serviceDetails", serviceDetails.get());
		
		return "/service_details/edit.jsp";
	}
	
	@PostMapping("/{id}")
	public String update(@PathVariable int id, ServiceDetails serviceDetail, String depLocation, String arrLocation, Model model, Principal principal) {
		
		Optional<ServiceDetails> existingServiceDetails = serviceDetailsRepo.findById(id);		
		if(!existingServiceDetails.isPresent())
			return "redirect:/service_details/";
		
		Optional<Location> departureLocation = locationRepo.findByLocationName(depLocation);
		Optional<Location> arrivalLocation = locationRepo.findByLocationName(arrLocation);
		
		Optional<User> user = basicUtil.getUser(principal);
		
		if(user.isPresent() && departureLocation.isPresent() && arrivalLocation.isPresent() && user.get().getRole().equals("ROLE_OPERATOR")) {
			serviceDetail.setId(id);
			serviceDetail.setDepartureLocation(departureLocation.get());
			serviceDetail.setArrivalLocation(arrivalLocation.get());
			serviceDetail.genrateServiceName();
			serviceDetailsRepo.save(serviceDetail);
		}
		
		return "redirect:/service_details/";
	}
}
