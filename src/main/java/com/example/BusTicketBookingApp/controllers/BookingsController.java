package com.example.BusTicketBookingApp.controllers;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.BusTicketBookingApp.daos.LocationRepo;
import com.example.BusTicketBookingApp.daos.ScheduleRepo;
import com.example.BusTicketBookingApp.daos.ServiceDetailsRepo;
import com.example.BusTicketBookingApp.models.Location;
import com.example.BusTicketBookingApp.models.Schedule;
import com.example.BusTicketBookingApp.models.ServiceDetails;

@Controller
@RequestMapping("/bookings")
public class BookingsController {
	
	@Autowired
	LocationRepo locationRepo;
	
	@Autowired
	ServiceDetailsRepo serviceDetailsRepo;

	@Autowired
	ScheduleRepo scheduleRepo;
	
	@GetMapping("/new")
	public String newBooking(Model model) {
		
		List<String> locations = locationRepo.findAllProjectedByLocationName();
		model.addAttribute("locations", locations);
		return "/bookings/new.jsp";
	}
	
	@GetMapping("/get")
	public String getBuses(String depLocation, String arrLocation, Date date) {
		
		Optional<Location> departureLocation = locationRepo.findByLocationName(depLocation);
		Optional<Location> arrivalLocation = locationRepo.findByLocationName(arrLocation);
		
		List<ServiceDetails> serviceDetails = null;
		List<Schedule> schedules = new LinkedList<>();
		
		if(departureLocation.isPresent() && arrivalLocation.isPresent()) {
			if(departureLocation.get().getId() != arrivalLocation.get().getId()) {
				serviceDetails = serviceDetailsRepo.finAllByDepartureLocationAndArrivalLocation(departureLocation.get().getId(), arrivalLocation.get().getId());
				for(ServiceDetails service: serviceDetails) {
					schedules.addAll(scheduleRepo.findAllByServiceDetailsAndWeekDay(service.getId(), date.getDay() + 1));
				}
				
				for(Schedule s: schedules) {
					System.out.println(s.getBusDetails().getBusName() + "  " + s.getBasePrice());
				}
			}
		}
		
		
		
		
		return "/";
//		return "/bookings/new.jsp";	
//		return "/bookings/get_buses.jsp";
	}
}
