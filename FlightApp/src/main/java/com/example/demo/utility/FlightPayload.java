package com.example.demo.utility;

import java.util.List;

public class FlightPayload {
	public FlightPayload(List<String> statusMessage) {
		super();
		this.statusMessage = statusMessage;
	}
	private int statusCode;
	private List<String> statusMessage;
	
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public List<String>  getStatusMessage() {
		return statusMessage;
	}
	public void setStatusMessage(List<String>  statusMessage) {
		this.statusMessage = statusMessage;
	}
	
}
