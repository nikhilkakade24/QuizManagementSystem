package com.epam.service;

import java.util.List;

import com.epam.dto.QuizDto;
import com.epam.model.Quiz;

public interface QuizService {
	public QuizDto saveQuiz(QuizDto quizDto);

	public QuizDto deleteQuiz(int i);

	public List<QuizDto> getQuizList();

	public QuizDto updateTitleQuiz(int quizid, String title);

	public QuizDto updateQuizMarks(int quizId, double marks);

//	public String getQuizName(int id);
	
	Quiz dtoToEntity(QuizDto quizDto);
	QuizDto entityToDto(Quiz quiz);

	public String getQuizName(int id);

}
