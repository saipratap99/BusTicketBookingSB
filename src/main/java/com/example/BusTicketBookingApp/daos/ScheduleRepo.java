package com.example.BusTicketBookingApp.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.BusTicketBookingApp.models.Schedule;

public interface ScheduleRepo extends JpaRepository<Schedule, Integer> {

}
