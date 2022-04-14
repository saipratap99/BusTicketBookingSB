package com.example.BusTicketBookingApp.controllers;

import java.io.IOException;
import java.security.Principal;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.BusTicketBookingApp.daos.BusDetailsRepo;
import com.example.BusTicketBookingApp.daos.ScheduleRepo;
import com.example.BusTicketBookingApp.daos.ServiceDetailsRepo;
import com.example.BusTicketBookingApp.daos.UserRepo;
import com.example.BusTicketBookingApp.models.BusDetails;
import com.example.BusTicketBookingApp.models.Schedule;
import com.example.BusTicketBookingApp.models.ServiceDetails;
import com.example.BusTicketBookingApp.models.User;
import com.example.BusTicketBookingApp.utils.BasicUtil;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {
	
	@Autowired
	BasicUtil basicUtil;
	
	@Autowired
	ServiceDetailsRepo serviceDetailsRepo;
	
	@Autowired
	BusDetailsRepo busDetailsRepo;
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	ScheduleRepo scheduleRepo;
	
	@GetMapping("/new")
	public String newSchedule(Principal principal, Model model){
		
		List<BusDetails> buses = busDetailsRepo.findAll();
		List<String> services = serviceDetailsRepo.findAllProjectedByServiceName();
		model.addAttribute("services", services);
		model.addAttribute("buses", buses);
		
		basicUtil.addNavBarAttributesToModel(principal, model);
		
		return "/schedule/new.jsp";
	}
	
	@PostMapping("/create")
	public String create(Schedule schedule, String bus, String service, String depTime, String tripDuration, String week, Principal principal) throws ParseException {
		
		
		schedule.setDepartureTime(basicUtil.parseStringToSqlTime(depTime));
		
		int minutes = Integer.parseInt(tripDuration.split(":")[0])*60 + Integer.parseInt(tripDuration.split(":")[1]);
		schedule.setDuration(minutes);
		
		User user = userRepo.findByEmail(principal.getName());
		
		Optional<ServiceDetails> serviceDetails = serviceDetailsRepo.findByServiceName(service);
		Optional<BusDetails> busDetails = busDetailsRepo.findById(Integer.parseInt(bus));
		
		if(user != null && user.getRole().equals("ROLE_OPERATOR") && busDetails.isPresent() && serviceDetails.isPresent() && week.matches("[1-7]{1}")) {
			schedule.setBusDetails(busDetails.get());
			schedule.setServiceDetails(serviceDetails.get());
			schedule.setWeekDay(Integer.parseInt(week));
			
			scheduleRepo.save(schedule);
		}
			
		return "redirect:/schedule/new.jsp";
	}
}
