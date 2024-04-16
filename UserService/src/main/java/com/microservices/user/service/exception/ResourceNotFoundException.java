package com.microservices.user.service.exception;

public class ResourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6610742738232019141L;

	ResourceNotFoundException() {
		super("Resource not found on server !!");
	}

	public ResourceNotFoundException(String message) {
		super(message);
	}

}
