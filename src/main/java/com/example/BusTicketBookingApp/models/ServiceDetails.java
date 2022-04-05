package com.example.BusTicketBookingApp.models;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "service_details")
public class ServiceDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "service_name", nullable = false)
	private String serviceName;
	
	@Column(name = "service_number", nullable = false)
	private String serviceNumber;
	
	@Column(name = "service_type", nullable = false)
	private String serviceType;
	
	@Column(name = "departure_date", nullable = false)
	private Date departureDate;
	
	@Column(name = "arrival_date", nullable = false)
	private Date arrivalDate;

	@Column(name = "departure_time", nullable = false)
	private Time departureTime;
	
	@Column(name = "arrival_time", nullable = false)
	private Time arrivalTime;
	
	@Column(nullable = false)
	private double distance;

	@OneToMany(mappedBy = "serviceDetails") // One service may have many buses
	@LazyCollection(LazyCollectionOption.FALSE)
	List<BusDetails> busDetails;
	
	@ManyToOne // many services may have same departure location.
	private Location departureLocation;
	
	@ManyToOne // many services may have same arrival location. 
	private Location arrivalLocation;

	@OneToMany(mappedBy = "serviceDetails")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<BookingDetails> bookingDetails;
	
	// driver, conductor, created_at, updated_at

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceNumber() {
		return serviceNumber;
	}

	public void setServiceNumber(String serviceNumber) {
		this.serviceNumber = serviceNumber;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
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

	public List<BusDetails> getBusDetails() {
		return busDetails;
	}

	public void setBusDetails(List<BusDetails> busDetails) {
		this.busDetails = busDetails;
	}

	public Location getDepartureLocation() {
		return departureLocation;
	}

	public void setDepartureLocation(Location departureLocation) {
		this.departureLocation = departureLocation;
	}

	public Location getArrivalLocation() {
		return arrivalLocation;
	}

	public void setArrivalLocation(Location arrivalLocation) {
		this.arrivalLocation = arrivalLocation;
	}

	public List<BookingDetails> getBookingDetails() {
		return bookingDetails;
	}

	public void setBookingDetails(List<BookingDetails> bookingDetails) {
		this.bookingDetails = bookingDetails;
	}
	
	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	
}
