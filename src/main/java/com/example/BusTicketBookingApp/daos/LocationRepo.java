package com.example.BusTicketBookingApp.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.BusTicketBookingApp.models.Location;

public interface LocationRepo extends JpaRepository<Location, Integer>{

}
