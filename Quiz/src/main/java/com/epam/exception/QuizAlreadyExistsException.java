package com.epam.exception;

public class QuizAlreadyExistsException extends RuntimeException{
	
	public QuizAlreadyExistsException(String message) {
		super(message);
	}
}
