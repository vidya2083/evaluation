package com.example.demo.web;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.FlightDTO;
import com.example.demo.service.FlightService;
import com.example.demo.utility.FlightNotFoundException;

import jakarta.validation.constraints.Pattern;

@RestController
@Validated
public class FlightController {
	@Autowired
	FlightService service;

	@PostMapping("/flights")
	public ResponseEntity<FlightDTO> saveFlightDetails(@Validated @RequestBody FlightDTO dto) {
		return new ResponseEntity<>(service.saveFlightDetails(dto), HttpStatus.CREATED);
	}

	@PutMapping("/flights/{flightNo}")
	public ResponseEntity<FlightDTO> editFlightDetails(@Validated @PathVariable String flightNo, @RequestBody FlightDTO dto)
			throws FlightNotFoundException {
		dto.setFlightNo(flightNo);
		return new ResponseEntity<>(service.editFlightDetails(dto), HttpStatus.OK);
	}

	@GetMapping("/flights/{flightNo}")
	public ResponseEntity<FlightDTO> fetchFlightDetailsById(@Validated @PathVariable String flightNo) throws FlightNotFoundException {
		return new ResponseEntity<>(service.fetchFlightDetailsById(flightNo), HttpStatus.OK);
	}

	@GetMapping("/flights")
	public ResponseEntity<List<FlightDTO>> fetchAllFlights() throws FlightNotFoundException {
		return new ResponseEntity<>(service.fetchAllFlights(), HttpStatus.OK);
	}

	@GetMapping("/flights/{source}/{destination}")
	public ResponseEntity<List<FlightDTO>> fetchFlights(@Validated @Pattern(regexp="^[a-zA-Z\s]+$",message="{flight.source.invalid}") @PathVariable String source, 
														@Validated @Pattern(regexp="^[a-zA-Z\s]+$",message="{flight.destination.invalid}") @PathVariable String destination)
			throws FlightNotFoundException {
		return new ResponseEntity<>( service.fetchFlights(source, destination), HttpStatus.OK);
	}

	@DeleteMapping("/flights/{flightNo}")
	public ResponseEntity<String> deleteFlight(@Validated @PathVariable String flightNo) throws FlightNotFoundException {
		return new ResponseEntity<>(service.deleteFlight(flightNo), HttpStatus.OK);
	}

}
