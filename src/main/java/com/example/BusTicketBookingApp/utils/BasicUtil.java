package com.example.BusTicketBookingApp.utils;

import java.security.Principal;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.example.BusTicketBookingApp.daos.UserRepo;
import com.example.BusTicketBookingApp.models.User;

@Component
public class BasicUtil {
	
	@Autowired 
	UserRepo userRepo;
	
	public Time parseStringToSqlTime(String time) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		return new Time(sdf.parse(time).getTime());
	}
	
	public Optional<User> getUser(Principal princpal){
		if(princpal == null)
			return Optional.ofNullable(null);
		return Optional.ofNullable(userRepo.findByEmail(princpal.getName()));		
	}
	
	public void addNavBarAttributesToModel(Principal principal, Model model) {
		Optional<User> user = getUser(principal); 
		
		if(user.isPresent()) {
			model.addAttribute("role", user.get().getRole());
			model.addAttribute("isOperator", "ROLE_OPERATOR".equals(user.get().getRole()));
			model.addAttribute("isAdmin", "ROLE_ADMIN".equals(user.get().getRole()));
		}
		
	}
	
	public String getWeekDay(int week) {
		switch(week){
			case 1:
				return "Sunday";
			case 2:
				return "Monday";
			case 3:
				return "Tuesday";
			case 4:
				return "Wednesday";
			case 5:
				return "Thursday";
			case 6:
				return "Friday";
			case 7:
				return "Saturday";
		}
		return "";
	}
	
	
}
