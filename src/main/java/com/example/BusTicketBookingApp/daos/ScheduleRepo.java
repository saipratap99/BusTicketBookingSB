package com.example.BusTicketBookingApp.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.BusTicketBookingApp.models.Schedule;
import com.example.BusTicketBookingApp.models.ServiceDetails;

public interface ScheduleRepo extends JpaRepository<Schedule, Integer> {
	
	@Query("from Schedule where serviceDetails.id = :serviceId and weekDay = :week")
	List<Schedule> findAllByServiceDetailsAndWeekDay(int serviceId, int week);
}
