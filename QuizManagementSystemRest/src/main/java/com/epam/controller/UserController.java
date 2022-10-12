package com.epam.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.dto.QuestionDto;
import com.epam.dto.UserDto;
import com.epam.service.QuestionService;
import com.epam.service.QuizService;
import com.epam.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;
	@Autowired
	QuizService quizService;

	@Autowired
	QuestionService questionService;

	@Autowired
	UserService useService;

	List<QuestionDto> questionList = new ArrayList<>();

	@PreAuthorize("hasAnyAuthority('USER','ADMIN')")
	@PostMapping
	public UserDto userRegister(@RequestBody @Valid UserDto userDto) {

		userService.addUser(userDto);

		return userDto;

	}

	@PreAuthorize("hasAnyAuthority('USER','ADMIN')")
	@PostMapping("/questionlist")
	public void questionList(@RequestBody List<QuestionDto> questionList) {

		this.questionList = questionList;

	}

	@PreAuthorize("hasAnyAuthority('USER','ADMIN')")
	@PostMapping("/result")
	public long calculateResult(@RequestBody List<Integer> options) {

		return userService.calculateResult(questionList, options);

	}

}
