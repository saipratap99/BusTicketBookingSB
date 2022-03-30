package com.example.BusTicketBookingApp.services;

import javax.servlet.http.Cookie;

import org.springframework.stereotype.Service;

@Service
public class CookieService {
	public Cookie getJwtCookie(String token) {
		Cookie cookie = new Cookie("jwt", token);
		cookie.setPath("/");
		cookie.setMaxAge(100000);
		cookie.setSecure(false);
		cookie.setHttpOnly(true);
		return cookie;
	}
}
