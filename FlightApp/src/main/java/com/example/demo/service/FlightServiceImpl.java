package com.example.demo.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.FlightDTO;
import com.example.demo.entity.FlightEntity;
import com.example.demo.repository.FlightRepository;
import com.example.demo.utility.FlightNotFoundException;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class FlightServiceImpl implements FlightService {
	@Autowired
	FlightRepository repository;
	private String message = "Flight details not found" ;
	@Override
	public FlightDTO saveFlightDetails(FlightDTO dto) {
		FlightEntity returnedEntity = repository.saveAndFlush(FlightDTO.prepareEntity(dto));
		return FlightDTO.prepareDTO(returnedEntity);

	}

	@Override
	public FlightDTO editFlightDetails(FlightDTO dto) throws FlightNotFoundException {
		FlightEntity flight = repository.findById(dto.getFlightNo())
				.orElseThrow(() -> new FlightNotFoundException(message));

		flight.setDestination(dto.getDestination());
		flight.setDuration(dto.getDuration());
		flight.setSource(dto.getSource());
		flight.setFlightNo(dto.getFlightNo());

		repository.saveAndFlush(flight);
		return FlightDTO.prepareDTO(flight);

	}

	@Override
	public FlightDTO fetchFlightDetailsById(String flightNo) throws FlightNotFoundException {

		FlightEntity flight = repository.findById(flightNo)
				.orElseThrow(() -> new FlightNotFoundException(message));

		return FlightDTO.prepareDTO(flight);

	}

	@Override
	public List<FlightDTO> fetchAllFlights() throws FlightNotFoundException {

		List<FlightEntity> entityList = Optional.of(repository.findAll()).filter(flightList -> !flightList.isEmpty())
				.orElseThrow(() -> new FlightNotFoundException(message)).stream()
				.sorted(Comparator.comparing(FlightEntity::getDuration)).toList();
		return prepareDTOList(entityList);

	}

	@Override
	public List<FlightDTO> fetchFlights(String source, String destination) throws FlightNotFoundException {
		List<FlightEntity> entityList = Optional.of(repository.fetchFlight(source, destination))
				.filter(flightList -> !flightList.isEmpty())
				.orElseThrow(() -> new FlightNotFoundException(message)).stream()
				.sorted(Comparator.comparing(FlightEntity::getDuration)).toList();

		return prepareDTOList(entityList);

	}

	@Override
	public String deleteFlight(String flightNo) throws FlightNotFoundException {

		FlightEntity flight = repository.findById(flightNo)
				.orElseThrow(() -> new FlightNotFoundException(message));
		repository.delete(flight);
		return "Flight details deleted successfully";

	}

	private List<FlightDTO> prepareDTOList(List<FlightEntity> entityList) {
		return entityList.stream().map(FlightDTO::prepareDTO).toList();

	}
}
