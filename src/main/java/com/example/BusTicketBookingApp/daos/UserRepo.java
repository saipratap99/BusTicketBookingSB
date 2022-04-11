package com.example.BusTicketBookingApp.daos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.BusTicketBookingApp.models.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	User findByEmail(String email);

	boolean existsByEmail(String email);
}
