package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.FlightEntity;

public interface FlightRepository extends JpaRepository<FlightEntity, String> {
	
	@Query("Select e from FlightEntity e where e.source = ?1 and e.destination =?2")
	List<FlightEntity> fetchFlight(String source, String destination);
}
