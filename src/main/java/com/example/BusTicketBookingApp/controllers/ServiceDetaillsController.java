package com.example.BusTicketBookingApp.controllers;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.example.BusTicketBookingApp.daos.LocationRepo;
import com.example.BusTicketBookingApp.daos.ServiceDetailsRepo;
import com.example.BusTicketBookingApp.models.Location;
import com.example.BusTicketBookingApp.models.ServiceDetails;
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
	BasicUtil basicUtil;
	
	@GetMapping("/new")
	public String newBusDetails(Model model) {
		List<String> locations = locationRepo.findAllProjectedByLocationName(); 

		model.addAttribute("locations", locations);
		
		return "/service_details/new.jsp";
	}
	
	@PostMapping("/create")
	public String create(ServiceDetails serviceDetail, String depLocation, String arrLocation, String depTime, String arrTime, Model model) throws ParseException {
		
//		serviceDetail.setDepartureTime(basicUtil.parseStringToSqlTime(depTime));
//		serviceDetail.setArrivalTime(basicUtil.parseStringToSqlTime(arrTime));
//		
//		Optional<Location> departureLocation = locationRepo.findByLocationName(depLocation);
//		Optional<Location> arrivalLocation = locationRepo.findByLocationName(arrLocation);
//		
//		if(departureLocation.isPresent() && arrivalLocation.isPresent()) {
//			serviceDetail.setDepartureLocation(departureLocation.get());
//			serviceDetail.setArrivalLocation(arrivalLocation.get());
//			serviceDetail.genrateServiceName();
//			serviceDetailsRepo.save(serviceDetail);
//		}
//		
		return "redirect:/service_details/new";
	}
}
