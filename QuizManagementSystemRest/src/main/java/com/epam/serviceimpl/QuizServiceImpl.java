package com.epam.serviceimpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.dto.QuizDto;
import com.epam.exception.InvalidQuizException;
import com.epam.model.Quiz;
import com.epam.repository.QuizRepository;
import com.epam.service.QuizService;

@Service
public class QuizServiceImpl implements QuizService {

	@Autowired
	QuizRepository quizRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public QuizDto saveQuiz(QuizDto quizDto) {

		Quiz quiz = dtoToEntity(quizDto);

		quiz.getQuestionList().stream().forEach(q -> q.setQuiz(quiz));

		return entityToDto(quizRepository.save(quiz));

	}

	@Override
	public QuizDto deleteQuiz(int id) {

		Quiz quiz = quizRepository.findById(id)
				.orElseThrow(() -> new InvalidQuizException("Quiz not found with id:" + id));

		quizRepository.deleteById(id);

		return entityToDto(quiz);

	}

	@Override
	public List<QuizDto> getQuizList() {

		List<Quiz> quizList = quizRepository.findAll();

		return quizList.stream().map(q -> modelMapper.map(q, QuizDto.class)).collect(Collectors.toList());

	}

	@Override
	@Transactional
	public QuizDto updateTitleQuiz(int quizId, String title) {

		Quiz quiz = quizRepository.findById(quizId)
				.orElseThrow(() -> new InvalidQuizException("Quiz not found with id:" + quizId));

		quiz.setTitle(title);
		quizRepository.save(quiz);

		return entityToDto(quiz);

	}

	@Override
	public QuizDto updateQuizMarks(int quizId, double marks) {
		Quiz quiz = quizRepository.findById(quizId)
				.orElseThrow(() -> new InvalidQuizException("Quiz not found with id:" + quizId));

		quiz.setTotalMarks(marks);
		quizRepository.save(quiz);

		return entityToDto(quiz);

	}

	@Override
	public Quiz dtoToEntity(QuizDto quizDto) {

		return modelMapper.map(quizDto, Quiz.class);
	}

	@Override
	public QuizDto entityToDto(Quiz quiz) {

		return modelMapper.map(quiz, QuizDto.class);

	}

	@Override
	public String getQuizName(int id) {
		Quiz quiz = quizRepository.findById(id)
				.orElseThrow(() -> new InvalidQuizException("Quiz not found with id:" + id));

		return quiz.getTitle();

	}

}
