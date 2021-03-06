package com.example.BusTicketBookingApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.BusTicketBookingApp.daos.UserRepo;
import com.example.BusTicketBookingApp.models.User;

@Service
public class UserService {
	@Autowired
	UserRepo userRepo;
	
	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	public User save(User user) {
		String encrytedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encrytedPassword);
		return this.userRepo.save(user);
	}
}
