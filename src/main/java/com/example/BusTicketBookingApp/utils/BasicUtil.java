package com.example.BusTicketBookingApp.utils;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

@Component
public class BasicUtil {
	public Time parseStringToSqlTime(String time) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		return new Time(sdf.parse(time).getTime());
	}
}
