package com.lcwd.hotel.exception;

public class ResourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException() {
		super("Resource not found exception !!");
	}

	public ResourceNotFoundException(String s) {
		super(s);
	}

}
