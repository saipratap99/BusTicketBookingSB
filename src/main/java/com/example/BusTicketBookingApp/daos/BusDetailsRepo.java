package com.example.BusTicketBookingApp.daos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.BusTicketBookingApp.models.BusDetails;

public interface BusDetailsRepo extends JpaRepository<BusDetails, Integer>{
	
}
