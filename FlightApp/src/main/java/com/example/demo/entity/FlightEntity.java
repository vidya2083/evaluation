package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Flights")
public class FlightEntity {
	@Id
	private String flightNo;
	@Column(length = 50)
	private String source;
	@Column(length = 50)
	private String destination;
	private Integer duration;

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

	@Override
	public String toString() {
		return "FlightEntity [flightNo=" + flightNo + ", source=" + source + ", destination=" + destination
				+ ", duration=" + duration + "]";
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

}
