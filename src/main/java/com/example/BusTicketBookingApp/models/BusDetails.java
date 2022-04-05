package com.example.BusTicketBookingApp.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bus_details")
public class BusDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "bus_name", nullable = false)
	private String busName;
	
	@Column(name = "seat_count", nullable = false)
	private int seatCount;
	
	@Column(name = "bus_reg_number", nullable = false)
	private String busRegNumber;
	
	@Column(name = "bus_type", nullable = false)
	private String busType;
	
	@Column(name = "seating_type", nullable = false)
	private String seatingType;
	
	@Column(name = "base_price", nullable = false, columnDefinition = "Decimal(10,2) default '0.0'")
	private double basePrice;
	
	
	@ManyToOne
	private Location currLocation;
	
	@Column(name = "available_seats", nullable = false)
	private int availableSeats;
	
	@Column(name = "last_maintance", nullable = false)
	private Date lastMaintance;
	
	@Column(name = "on_service", nullable = false)
	private Date onService;
	
	@ManyToOne // many bus details belongs to one service
	private ServiceDetails serviceDetails;
	
	// created_at, updated_at

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBusName() {
		return busName;
	}

	public void setBusName(String busName) {
		this.busName = busName;
	}

	public int getSeatCount() {
		return seatCount;
	}

	public void setSeatCount(int seatCount) {
		this.seatCount = seatCount;
	}

	public String getBusRegNumber() {
		return busRegNumber;
	}

	public void setBusRegNumber(String busRegNumber) {
		this.busRegNumber = busRegNumber;
	}

	public String getBusType() {
		return busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}

	public String getSeatingType() {
		return seatingType;
	}

	public void setSeatingType(String seatingType) {
		this.seatingType = seatingType;
	}

	public Location getCurrLocation() {
		return currLocation;
	}

	public void setCurrLocation(Location currLocation) {
		this.currLocation = currLocation;
	}

	public Date getLastMaintance() {
		return lastMaintance;
	}

	public void setLastMaintance(Date lastMaintance) {
		this.lastMaintance = lastMaintance;
	}

	public Date getOnService() {
		return onService;
	}

	public void setOnService(Date onService) {
		this.onService = onService;
	}

	public ServiceDetails getServiceDetailsId() {
		return serviceDetails;
	}

	public void setServiceDetailsId(ServiceDetails serviceDetails) {
		this.serviceDetails = serviceDetails;
	}
	
	public int getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}

	public ServiceDetails getServiceDetails() {
		return serviceDetails;
	}

	public void setServiceDetails(ServiceDetails serviceDetails) {
		this.serviceDetails = serviceDetails;
	}
	
	public double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

	
}


