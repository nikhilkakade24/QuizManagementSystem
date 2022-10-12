package com.epam.exception;

public class InvalidDetailsException extends RuntimeException{
	public InvalidDetailsException(String msg) {
		super(msg);
	}

}
