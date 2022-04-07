package com.example.BusTicketBookingApp.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.BusTicketBookingApp.models.ServiceDetails;

public interface ServiceDetailsRepo extends JpaRepository<ServiceDetails, Integer> {

}
