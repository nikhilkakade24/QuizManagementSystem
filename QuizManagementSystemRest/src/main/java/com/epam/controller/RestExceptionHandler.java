package com.epam.controller;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.epam.exception.InvalidDetailsException;
import com.epam.exception.InvalidQuestionException;
import com.epam.exception.InvalidQuizException;

@ControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(value = InvalidDetailsException.class)
	public ResponseEntity<ExceptionResponse> invalidDetailsException(Exception exception, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setError(exception.getMessage());
		exceptionResponse.setStatus(HttpStatus.UNAUTHORIZED.name());
		exceptionResponse.setTimeStamp(LocalDate.now().toString());
		exceptionResponse.setPath(request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(value = InvalidQuizException.class)
	public ResponseEntity<ExceptionResponse> invalidQuizException(Exception exception, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setError(exception.getMessage());
		exceptionResponse.setStatus(HttpStatus.NOT_FOUND.name());
		exceptionResponse.setTimeStamp(LocalDate.now().toString());
		exceptionResponse.setPath(request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}

	

	@ExceptionHandler(value = InvalidQuestionException.class)
	public ResponseEntity<ExceptionResponse> invalidQuestionException(Exception exception, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setError(exception.getMessage());
		exceptionResponse.setStatus(HttpStatus.NOT_FOUND.name());
		exceptionResponse.setTimeStamp(LocalDate.now().toString());
		exceptionResponse.setPath(request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<ExceptionResponse> methodArgumentNotValidException(MethodArgumentNotValidException exception,
			WebRequest request) {

		ExceptionResponse exceptionResponse = new ExceptionResponse();
		String str = exception.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage()).reduce("",(a, b) -> a + " ," + b);

		exceptionResponse.setError(str);
		exceptionResponse.setStatus(HttpStatus.BAD_REQUEST.name());
		exceptionResponse.setTimeStamp(LocalDate.now().toString());
		exceptionResponse.setPath(request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

}
