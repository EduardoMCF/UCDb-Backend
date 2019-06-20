package com.psoft.UCDb.exceptions;

public class UserNotFoundException extends RuntimeException {
	public UserNotFoundException( String message) {
		super(message);
	}
}
