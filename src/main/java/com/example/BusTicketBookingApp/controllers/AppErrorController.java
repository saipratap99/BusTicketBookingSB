package com.example.BusTicketBookingApp.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//public class AppErrorController implements ErrorController{
public class AppErrorController{	
//	@RequestMapping("/error")
	public String handleError(HttpServletRequest httpServletRequest) {
		
		Object status = httpServletRequest.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		if(status != null) {
			Integer statusCode = Integer.parseInt(status.toString());
			if(statusCode == HttpStatus.NOT_FOUND.value())
				return "/errors/error-404.jsp";
			if(statusCode == HttpStatus.FORBIDDEN.value())
				return "/errors/error-403.jsp";
		}
		return "/errors/error.jsp";
	}
}
