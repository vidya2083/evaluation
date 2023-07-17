package com.example.demo.dto;

import org.hibernate.validator.constraints.Range;

import com.example.demo.entity.FlightEntity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class FlightDTO {

	@NotEmpty(message = "{flight.flightNo.notEmpty}")
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "{flight.flightNo.invalid}")
	private String flightNo;

	@NotEmpty(message = "{flight.source.notEmpty}")
	@Pattern(regexp = "^[a-zA-Z\s]+$", message = "{flight.source.invalid}")
	private String source;

	@NotEmpty(message = "{flight.destination.notEmpty}")
	@Pattern(regexp = "^[a-zA-Z\s]+$", message = "{flight.destination.invalid}")
	private String destination;

	@Range(min = 10, message = "{flight.duration.invalid}")
	private int duration;

	public String getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public FlightDTO() {
		super();
	}

	public FlightDTO(
			@NotEmpty(message = "{flight.flightNo.notEmpty}") @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "{flight.flightNo.invalid}") String flightNo,
			@NotEmpty(message = "{flight.source.notEmpty}") @Pattern(regexp = "^[a-zA-Z ]+$", message = "{flight.source.invalid}") String source,
			@NotEmpty(message = "{flight.destination.notEmpty}") @Pattern(regexp = "^[a-zA-Z ]+$", message = "{flight.destination.invalid}") String destination,
			@Range(min = 10, message = "{flight.duration.invalid}") int duration) {
		super();
		this.flightNo = flightNo;
		this.source = source;
		this.destination = destination;
		this.duration = duration;
	}

	public static FlightEntity prepareEntity(FlightDTO dto) {
		FlightEntity entity = new FlightEntity();
		entity.setFlightNo(dto.getFlightNo());
		entity.setSource(dto.getSource());
		entity.setDestination(dto.getDestination());
		entity.setDuration(dto.getDuration());
		return entity;
	}

	public static FlightDTO prepareDTO(FlightEntity entity) {
		FlightDTO dto = new FlightDTO();
		dto.setFlightNo(entity.getFlightNo());
		dto.setSource(entity.getSource());
		dto.setDestination(entity.getDestination());
		dto.setDuration(entity.getDuration());
		return dto;
	}
}
