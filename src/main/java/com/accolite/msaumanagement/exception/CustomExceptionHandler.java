package com.accolite.msaumanagement.exception;

public class CustomExceptionHandler extends RuntimeException {// Custom Exception Class
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;

	public CustomExceptionHandler() {

	}

	public String getMessage() {
		return message;
	}

	public CustomExceptionHandler(String message) {
		this.message = message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
