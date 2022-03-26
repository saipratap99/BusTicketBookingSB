package com.example.BusTicketBookingApp.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.BusTicketBookingApp.models.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}
