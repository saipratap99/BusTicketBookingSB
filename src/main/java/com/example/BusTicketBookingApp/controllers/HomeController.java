package com.example.BusTicketBookingApp.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	@GetMapping("/")
	public ModelAndView index(HttpServletRequest req) {
		HttpSession session = req.getSession();
		System.out.println("user: " + req.getRemoteUser());
		System.out.println("cookies: " + req.getCookies());
		System.out.println(req);
//		
		ModelAndView indexView = new ModelAndView("/home/index.jsp");
		return indexView;
	}
}