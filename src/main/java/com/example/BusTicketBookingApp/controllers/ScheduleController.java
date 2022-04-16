package com.example.BusTicketBookingApp.controllers;

import java.io.IOException;
import java.security.Principal;
import java.sql.Date;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

	
	@GetMapping("/")
	public String getSchedules(Principal principal, Model model) {
		List<Schedule> schedules = scheduleRepo.findAll();
		basicUtil.addNavBarAttributesToModel(principal, model);
		model.addAttribute("schedules",schedules);
		return "/schedule/index.jsp";
	}
	
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
		
		createScheduleObject(schedule, depTime, tripDuration, service, bus, week, principal);
		
		return "redirect:/schedule/new.jsp";
		
	}
	
	@GetMapping("/{id}/edit")
	public String editForm(@PathVariable int id, Principal principal, Model model) throws ParseException {
		
		Optional<Schedule> schedule = scheduleRepo.findById(id);
		
		if(!schedule.isPresent())
			return "redirect:/schedule/";
		
		List<BusDetails> buses = busDetailsRepo.findAll();
		List<String> services = serviceDetailsRepo.findAllProjectedByServiceName();
		
		model.addAttribute("services", services);
		model.addAttribute("buses", buses);
		model.addAttribute("id", id);
		
		model.addAttribute("currServiceDetails", schedule.get().getServiceDetails().getServiceName());
		model.addAttribute("currBusDetails", schedule.get().getBusDetails().getId());
		model.addAttribute("currWeekDay", schedule.get().getWeekDay());
		model.addAttribute("currDep", schedule.get().getDepartureTime().toString());
		model.addAttribute("currDuration", (basicUtil.parseStringToSqlTime(schedule.get().getDuration()/60 + ":" + schedule.get().getDuration()%60)).toString());
		model.addAttribute("currBasePrice", schedule.get().getBasePrice());
		
		basicUtil.addNavBarAttributesToModel(principal, model);
	
		return "/schedule/edit.jsp";
	}
	
	@GetMapping("/{id}")
	public String getSchedule(@PathVariable int id,Model model, Principal principal) throws ParseException {
		Optional<Schedule> schedule = scheduleRepo.findById(id);
		
		if(!schedule.isPresent())
			return "redirect:/schedule/";
		
		String weeks[] = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
		
		model.addAttribute("schedule", schedule);
		model.addAttribute("currServiceDetails", schedule.get().getServiceDetails().getServiceName());
		model.addAttribute("currBusDetails", schedule.get().getBusDetails().getBusName());
		model.addAttribute("currWeekDay", weeks[schedule.get().getWeekDay() - 1]);
		model.addAttribute("currDep", schedule.get().getDepartureTime().toString());
		model.addAttribute("currDuration", (basicUtil.parseStringToSqlTime(schedule.get().getDuration()/60 + ":" + schedule.get().getDuration()%60)).toString());
		model.addAttribute("currBasePrice", schedule.get().getBasePrice());
		
		basicUtil.addNavBarAttributesToModel(principal, model);
		
		return "/schedule/show.jsp";
				
	}
	
	@PostMapping(path = "/{id}")
	public String updateSchedule(@PathVariable int id, Schedule schedule, String bus, String service, String depTime, String tripDuration, String week, Principal principal) throws ParseException {
		
		Optional<Schedule> exsitingSchedule = scheduleRepo.findById(id);
		
		if(!exsitingSchedule.isPresent())
			return "redirect:/schedule/";
		
		createScheduleObject(schedule, depTime, tripDuration, service, bus, week, principal);
		
		return "redirect:/schedule/";
	}
	
	private Optional<Schedule> createScheduleObject(Schedule schedule, String depTime, String tripDuration, String service, String bus, String week, Principal principal) throws ParseException {
		
		Optional<Schedule> scheduleOptional = Optional.ofNullable(null);
		
		schedule.setDepartureTime(basicUtil.parseStringToSqlTime(depTime));
		
		int minutes = Integer.parseInt(tripDuration.split(":")[0])*60 + Integer.parseInt(tripDuration.split(":")[1]);
		schedule.setDuration(minutes);
		
		Optional<User> user = basicUtil.getUser(principal);
		
		Optional<ServiceDetails> serviceDetails = serviceDetailsRepo.findByServiceName(service);
		Optional<BusDetails> busDetails = busDetailsRepo.findById(Integer.parseInt(bus));
		
		if(user.isPresent() && "ROLE_OPERATOR".equals(user.get().getRole()) && busDetails.isPresent() && serviceDetails.isPresent() && week.matches("[1-7]{1}")) {
			schedule.setBusDetails(busDetails.get());
			schedule.setServiceDetails(serviceDetails.get());
			schedule.setWeekDay(Integer.parseInt(week));
			
			scheduleRepo.save(schedule);
			scheduleOptional = Optional.ofNullable(schedule);
		}
		
		
		return scheduleOptional;
	}
}

