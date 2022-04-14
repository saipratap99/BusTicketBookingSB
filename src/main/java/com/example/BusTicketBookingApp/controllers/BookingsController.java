package com.example.BusTicketBookingApp.controllers;

import java.security.Principal;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.BusTicketBookingApp.daos.LocationRepo;
import com.example.BusTicketBookingApp.daos.ScheduleRepo;
import com.example.BusTicketBookingApp.daos.ServiceDetailsRepo;
import com.example.BusTicketBookingApp.daos.UserRepo;
import com.example.BusTicketBookingApp.models.Location;
import com.example.BusTicketBookingApp.models.Schedule;
import com.example.BusTicketBookingApp.models.ServiceDetails;
import com.example.BusTicketBookingApp.models.User;
import com.example.BusTicketBookingApp.utils.BasicUtil;

@Controller
@RequestMapping("/bookings")
public class BookingsController {
	
	@Autowired
	LocationRepo locationRepo;
	
	@Autowired
	ServiceDetailsRepo serviceDetailsRepo;

	@Autowired
	ScheduleRepo scheduleRepo;
	
	@Autowired
	BasicUtil basicUtil;
	
	@GetMapping("/new")
	public String newBooking(Model model, Principal principal) {
		
		basicUtil.addNavBarAttributesToModel(principal, model);
		
		List<String> locations = locationRepo.findAllProjectedByLocationName();
		model.addAttribute("locations", locations);
		return "/bookings/new.jsp";
	}
	
	@GetMapping("/get")
	public String getBuses(String depLocation, String arrLocation, Date date,Model model, RedirectAttributes redirectAttributes, Principal principal) {
		
		List<String> locations = locationRepo.findAllProjectedByLocationName();
		
		model.addAttribute("locations", locations);
		model.addAttribute("arrLocation", arrLocation);
		model.addAttribute("depLocation", depLocation);
		model.addAttribute("date", date);
		
		
		Optional<Location> departureLocation = locationRepo.findByLocationName(depLocation);
		Optional<Location> arrivalLocation = locationRepo.findByLocationName(arrLocation);
		
		if(departureLocation.isPresent() && arrivalLocation.isPresent()) {
			if(departureLocation.get().getId() != arrivalLocation.get().getId()) {
				return "redirect:/bookings/get/" + depLocation+ "/" + departureLocation.get().getId() + "/" + arrLocation + "/" + arrivalLocation.get().getId() + "/" + date.toString();
			}
		}
		
		basicUtil.addNavBarAttributesToModel(principal, model);
		
		return "redirect:/bookings/new.jsp";
	}
	
	@GetMapping("/get/{depLocation}/{depId}/{arrLocation}/{arrId}/{date}")
	public String availableBuses(@PathVariable String depLocation,@PathVariable int depId,@PathVariable String arrLocation,@PathVariable int arrId,@PathVariable Date date, Model model, Principal principal) {
		
		basicUtil.addNavBarAttributesToModel(principal, model);
		
		List<ServiceDetails> serviceDetails = null;
		List<Schedule> schedules = new LinkedList<>();
		
		serviceDetails = serviceDetailsRepo.finAllByDepartureLocationAndArrivalLocation(depId, arrId);
		for(ServiceDetails service: serviceDetails) {
			schedules.addAll(scheduleRepo.findAllByServiceDetailsAndWeekDay(service.getId(), date.getDay() + 1));
		}
		
		model.addAttribute("schedules", schedules);
		
		return "/bookings/get_buses.jsp";
	}
	
	
}
