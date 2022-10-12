package com.epam.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.dto.QuestionDto;
import com.epam.exception.InvalidQuestionException;
import com.epam.exception.InvalidQuizException;
import com.epam.model.Question;
import com.epam.model.Quiz;
import com.epam.repository.QuestionRepository;
import com.epam.repository.QuizRepository;
import com.epam.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	QuestionRepository questionRepository;

	@Autowired
	QuizRepository quizRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Question createQuestion(String title, List<String> options, String difficultyLevel, int answer, double mark,
			Quiz quiz) {
		Question question = new Question();
		question.setTitle(title);
		question.setOptions(options);
		question.setDifficultyLevel(difficultyLevel);
		question.setAnswer(answer);
		question.setMark(mark);
		question.setQuiz(quiz);

		return question;
	}

	@Override
	@Transactional
	public QuestionDto deleteQuestion(int id) {

		Question question = questionRepository.findById(id)
				.orElseThrow(() -> new InvalidQuestionException("Question not found with id: " + id));

		questionRepository.deleteById(id);

		return entityToDto(question);

	}

	@Override
	public List<QuestionDto> getAllQuestions(int quizId) {

		List<Question> questions = questionRepository.findByQuizId(quizId)
				.orElseThrow(() -> new InvalidQuizException("Quiz not found with id: " + quizId));

		return questions.stream().map(quest -> modelMapper.map(quest, QuestionDto.class)).collect(Collectors.toList());

	}

	@Override
	@Transactional
	public QuestionDto updateQuestionTitle(int id, String title) {

		Question question = questionRepository.findById(id)
				.orElseThrow(() -> new InvalidQuestionException("Question not found with id: " + id));

		question.setTitle(title);
		question = questionRepository.save(question);

		return entityToDto(question);

	}

	@Override
	@Transactional
	public QuestionDto updateQuestionDifficultyLevel(int id, String difficultyLevel) {

		Question question = questionRepository.findById(id)
				.orElseThrow(() -> new InvalidQuestionException("Question not found with id: " + id));

		question.setDifficultyLevel(difficultyLevel);
		question = questionRepository.save(question);
		return entityToDto(question);

	}

	@Override
	@Transactional
	public QuestionDto updateQuestionMark(int id, double mark) {
		Question question = questionRepository.findById(id)
				.orElseThrow(() -> new InvalidQuestionException("Question not found with id: " + id));

		question.setMark(mark);
		question = questionRepository.save(question);

		return entityToDto(question);
	}

	@Override
	public List<QuestionDto> getQuestionsLibrary() {
		List<Question> questions = questionRepository.findAll();
		return questions.stream().map(quest -> modelMapper.map(quest, QuestionDto.class)).collect(Collectors.toList());

	}

	@Override
	public QuestionDto getQuestion(int questId) {

		Question question = questionRepository.findById(questId)
				.orElseThrow(() -> new InvalidQuestionException("Question not found with id: " + questId));

		return entityToDto(question);

	}

	
	@Override
	public QuestionDto saveQuestion(QuestionDto questionDto) {

		Question question = questionRepository.save(dtoToEntity(questionDto));
		return entityToDto(question);
	}
	
	@Override
	public Question dtoToEntity(QuestionDto questionDto) {

		return modelMapper.map(questionDto, Question.class);

	}

	@Override
	public QuestionDto entityToDto(Question question) {

		return modelMapper.map(question, QuestionDto.class);
	}

}
