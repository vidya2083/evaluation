package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.FlightDTO;
import com.example.demo.utility.FlightNotFoundException;

public interface FlightService {

	FlightDTO saveFlightDetails(FlightDTO dto);

	FlightDTO editFlightDetails(FlightDTO dto) throws FlightNotFoundException;

	FlightDTO fetchFlightDetailsById(String flightNo) throws FlightNotFoundException;

	List<FlightDTO> fetchAllFlights() throws FlightNotFoundException;

	List<FlightDTO> fetchFlights(String source, String destination) throws FlightNotFoundException;

	String deleteFlight(String dlightNo) throws FlightNotFoundException;

}