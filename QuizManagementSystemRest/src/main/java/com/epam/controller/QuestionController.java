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
import com.epam.service.QuestionService;

@RestController
@RequestMapping("/question")
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	@PreAuthorize("hasAnyAuthority('USER','ADMIN')")
	@GetMapping
	public List<QuestionDto> getQuestionLibrary() {

		return questionService.getQuestionsLibrary();

	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping
	public QuestionDto saveQuestion(@RequestBody QuestionDto questionDto) {

		return questionService.saveQuestion(questionDto);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/{questionId}")
	public QuestionDto getQuestion(@PathVariable("questionId") int questionId) {

		return questionService.getQuestion(questionId);

	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("/{questionId}")
	public QuestionDto deleteQuestion(@PathVariable("questionId") int questionId) {
		return questionService.deleteQuestion(questionId);

	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@PutMapping("title/{questionId}/{title}")
	public QuestionDto updateTitle(@PathVariable("questionId") int questionId,
			@PathVariable("title") String questionTitle) {

		return questionService.updateQuestionTitle(questionId, questionTitle);

	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@PutMapping("difficulty/{questionId}/{difficultyLevel}")
	public QuestionDto updateDifficulty(@PathVariable("questionId") int questionId,
			@PathVariable("difficultyLevel") String questionDifficultyLevel) {

		return questionService.updateQuestionDifficultyLevel(questionId, questionDifficultyLevel);

	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@PutMapping("mark/{questionId}/{updatedmark}")
	public QuestionDto updateMark(@PathVariable("questionId") int questionId,
			@PathVariable("updatedmark") int updatedMark) {

		return questionService.updateQuestionMark(questionId, updatedMark);

	}

}
