package com.epam.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.epam.exception.CrediantialsEmptyException;
import com.epam.exception.InvalidDetailsException;
import com.epam.exception.QuizAlreadyExistsException;

@ControllerAdvice
public class QuizManagementExceptionHandler {

	@ExceptionHandler(value = InvalidDetailsException.class)
	public ModelAndView invalidDetailsException(Exception exception) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("message", exception.getMessage());
		modelAndView.setViewName("error");
		return modelAndView;
	}

	@ExceptionHandler(value = QuizAlreadyExistsException.class)
	public ModelAndView quizAlreadyExists(RuntimeException exception) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("message", exception.getMessage());
		modelAndView.setViewName("error");
		return modelAndView;
	}

	@ExceptionHandler(value = CrediantialsEmptyException.class)
	public ModelAndView credentialsEmpty(RuntimeException exception) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("message", exception.getMessage());
		modelAndView.setViewName("error");
		return modelAndView;
	}

}
