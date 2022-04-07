package com.example.BusTicketBookingApp.daos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.BusTicketBookingApp.models.Location;

public interface LocationRepo extends JpaRepository<Location, Integer>{
	
	Optional<Location> findByLocationName(String locationName);
	
}
