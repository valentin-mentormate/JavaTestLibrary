package com.valentin.testLib.exceptions;

public class TestFailedException extends Exception {
	
	private static final long serialVersionUID = 134242342342L;
	private String message;
	
	public TestFailedException(String message) {
		this.setMessage(message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
