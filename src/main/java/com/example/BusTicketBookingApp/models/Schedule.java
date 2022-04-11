package com.example.BusTicketBookingApp.models;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "schedule")
public class Schedule {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "base_price", nullable = false, columnDefinition = "Decimal(10,2) default '0.0'")
	private double basePrice;
 
	@Column(name = "week_day")
	private int weekDay;
	
	@Column(name = "departure_date", nullable = true)
	private Date departureDate;
	
	@Column(name = "arrival_date", nullable = true)
	private Date arrivalDate;
    
	@Column(name = "departure_time", nullable = false)
	private Time departureTime;
	
	@Column(name = "arrival_time", nullable = false)
	private Time arrivalTime;
	
	
	
	
	@ManyToOne(optional = false)
	BusDetails busDetails;
	
	@ManyToOne(optional = false)
	ServiceDetails serviceDetails;
	
	@OneToMany(mappedBy = "schedule")
	List<BookingDetails> bookingDetails;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public Time getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Time departureTime) {
		this.departureTime = departureTime;
	}

	public Time getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Time arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public BusDetails getBusDetails() {
		return busDetails;
	}

	public void setBusDetails(BusDetails busDetails) {
		this.busDetails = busDetails;
	}

	public ServiceDetails getServiceDetails() {
		return serviceDetails;
	}

	public void setServiceDetails(ServiceDetails serviceDetails) {
		this.serviceDetails = serviceDetails;
	}

	public List<BookingDetails> getBookingDetails() {
		return bookingDetails;
	}

	public void setBookingDetails(List<BookingDetails> bookingDetails) {
		this.bookingDetails = bookingDetails;
	}

	public int getWeekDay() {
		return weekDay;
	}

	public void setWeekDay(int weekDay) {
		this.weekDay = weekDay;
	}
	
}
