package com.epam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.dto.QuestionDto;
import com.epam.dto.QuizDto;
import com.epam.service.QuestionService;
import com.epam.service.QuizService;

@RequestMapping("/quiz")
@RestController
public class QuizController {

	@Autowired
	QuizService quizService;

	@Autowired
	QuestionService questionService;

	@PreAuthorize("hasAnyAuthority('USER','ADMIN')")
	@GetMapping
	public List<QuizDto> getQuiz() {

		return quizService.getQuizList();

	}

	@PreAuthorize("hasAnyAuthority('USER','ADMIN')")
	@GetMapping("/questions/{quizId}")
	public List<QuestionDto> getQuestions(@PathVariable("quizId") int quizId) {

		return questionService.getAllQuestions(quizId);

	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@PutMapping("/{id}/{newname}")
	public QuizDto updatedName(@PathVariable("id") int id, @PathVariable("newname") String newName) {

		return quizService.updateTitleQuiz(id, newName);

	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("/{id}")
	public QuizDto deleteQuiz(@PathVariable("id") int id) {

		return quizService.deleteQuiz(id);

	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping
	public QuizDto submitQuiz(@RequestBody QuizDto quizDto) {

		return quizService.saveQuiz(quizDto);

	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/quizname/{id}")
	public String getQuizName(@PathVariable int id) {

		return quizService.getQuizName(id);

	}

}
