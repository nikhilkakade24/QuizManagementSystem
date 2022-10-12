package com.epam.service;

import java.util.List;

import com.epam.dto.QuestionDto;
import com.epam.model.Question;
import com.epam.model.Quiz;

public interface QuestionService {
	public Question createQuestion(String title, List<String> options, String difficultyLevel, int answer,
			double mark, Quiz quiz);

	public QuestionDto deleteQuestion(int id);
	
	public QuestionDto saveQuestion(QuestionDto questionDto);

	public List<QuestionDto> getAllQuestions(int id);

	public QuestionDto updateQuestionTitle(int id, String title);

	public QuestionDto updateQuestionDifficultyLevel(int id, String difficultyLevel);

	public QuestionDto updateQuestionMark(int id, double mark);
	public List<QuestionDto> getQuestionsLibrary();

	public QuestionDto getQuestion(int questId);
	
	Question dtoToEntity(QuestionDto questionDto);
	QuestionDto entityToDto(Question question);
	
}
