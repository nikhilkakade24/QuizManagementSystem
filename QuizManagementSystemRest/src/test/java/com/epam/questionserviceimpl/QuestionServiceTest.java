package com.epam.questionserviceimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.epam.dto.QuestionDto;
import com.epam.exception.InvalidQuestionException;
import com.epam.exception.InvalidQuizException;
import com.epam.model.Question;
import com.epam.model.Quiz;
import com.epam.repository.QuestionRepository;
import com.epam.serviceimpl.QuestionServiceImpl;

@ExtendWith(MockitoExtension.class)
class QuestionServiceTest {

	@InjectMocks
	QuestionServiceImpl questionServiceImpl;

	@Mock
	QuestionRepository questionRepository;

	@Mock
	ModelMapper modelMapper;

	Question question1;

	Question question2;

	QuestionDto questionDto1;

	QuestionDto questionDto2;

	List<Question> ls;
	List<QuestionDto> listDto;

	List<Question> list;

	Quiz quiz = new Quiz();

	@BeforeEach
	void init() {
		question1 = new Question("What is java?", Arrays.asList("true", "false"), "easy", 1, 1);

		question2 = new Question("Is java is oops language?", Arrays.asList("true", "false"), "easy", 1, 1);

		questionDto1 = new QuestionDto("What is java?", Arrays.asList("true", "false"), "easy", 1, 1);

		questionDto2 = new QuestionDto("Is java is oops language?", Arrays.asList("true", "false"), "easy", 1, 1);

		ls = Arrays.asList(question1, question2);
		listDto = Arrays.asList(questionDto1, questionDto2);

		list = new ArrayList<>(ls);

		quiz = new Quiz();

	}

	@Test
	void testQuestionListPresnt() {

		when(questionRepository.findByQuizId(1)).thenReturn(Optional.of(list));

		assertEquals(2, questionServiceImpl.getAllQuestions(1).size(), "Expected size of questions list");
	}

	@Test
	void testQuestioListAbsent() {

		when(questionRepository.findByQuizId(2)).thenThrow(InvalidQuizException.class);

		assertThrows(InvalidQuizException.class, () -> questionServiceImpl.getAllQuestions(2));
	}

	@Test
	void testDelete() {

		when(questionRepository.findById(1)).thenReturn(Optional.of(question1));

		doNothing().when(questionRepository).deleteById(1);

		questionRepository.deleteById(1);

		verify(questionRepository).deleteById(1);

		when(questionServiceImpl.entityToDto(question1)).thenReturn(questionDto1);

		assertEquals(questionDto1, questionServiceImpl.deleteQuestion(1));
	}

	@Test
	void testDeleteFailure() {

		when(questionRepository.findById(1)).thenThrow(InvalidQuestionException.class);

		assertThrows(InvalidQuestionException.class, () -> questionServiceImpl.deleteQuestion(1));
	}

	@Test
	void testeUpdateQuestionTitle() {

		when(questionRepository.findById(1)).thenReturn(Optional.of(question1));

		when(questionRepository.save(question1)).thenReturn(question1);

		when(questionServiceImpl.entityToDto(question1)).thenReturn(questionDto1);

		assertEquals(questionDto1, questionServiceImpl.updateQuestionTitle(1, "is java is platform dependent?"));
	}

	@Test
	void testUpdateQuestionTitleFailure() {

		when(questionRepository.findById(1)).thenThrow(InvalidQuestionException.class);
		assertThrows(InvalidQuestionException.class,
				() -> questionServiceImpl.updateQuestionTitle(1, "is java is platform dependent?"));

	}

	@Test
	void testUpdateQuestionDifficultyLevel() {

		when(questionRepository.findById(1)).thenReturn(Optional.of(question1));

		when(questionRepository.save(question1)).thenReturn(question1);

		when(questionServiceImpl.entityToDto(question1)).thenReturn(questionDto1);

		assertEquals(questionDto1, questionServiceImpl.updateQuestionDifficultyLevel(1, "medium"));
	}

	@Test
	void testUpdateQuestionDifficultyLevelFailure() {

		when(questionRepository.findById(1)).thenThrow(InvalidQuestionException.class);

		assertThrows(InvalidQuestionException.class,
				() -> questionServiceImpl.updateQuestionDifficultyLevel(1, "medium"));
	}

	@Test
	void testUpdateQuestionMarks() {

		when(questionRepository.findById(1)).thenReturn(Optional.of(question1));

		when(questionRepository.save(question1)).thenReturn(question1);

		when(questionServiceImpl.entityToDto(question1)).thenReturn(questionDto1);

		assertEquals(questionDto1, questionServiceImpl.updateQuestionMark(1, 2));
	}

	@Test
	void testUpdateQuestionMarksFailure() {

		when(questionRepository.findById(1)).thenThrow(InvalidQuestionException.class);

		assertThrows(InvalidQuestionException.class, () -> questionServiceImpl.updateQuestionMark(1, 2));
	}

	@Test
	void testCreateQuestion() {

		assertTrue(questionServiceImpl.createQuestion("What is java?", Arrays.asList("true", "false"), "easy", 1, 1,
				quiz) instanceof Question);
	}

	@Test
	void testGetQuestion() {
		when(questionRepository.findById(1)).thenReturn(Optional.of(question1));
		when(questionServiceImpl.entityToDto(question1)).thenReturn(questionDto1);

		assertEquals(questionDto1, questionServiceImpl.getQuestion(1));
	}

	@Test
	void testGetQuestionFailure() {

		when(questionRepository.findById(1)).thenThrow(InvalidQuestionException.class);

		assertThrows(InvalidQuestionException.class, () -> questionServiceImpl.getQuestion(1));
	}

	@Test
	void testSaveQuestion() {
		when(questionRepository.save(question1)).thenReturn(question1);
		when(questionServiceImpl.dtoToEntity(questionDto1)).thenReturn(question1);
		when(questionServiceImpl.entityToDto(question1)).thenReturn(questionDto1);

		assertEquals(questionDto1, questionServiceImpl.saveQuestion(questionDto1));
	}

	@Test
	void testDtoToEntity() {
		when(modelMapper.map(questionDto1, Question.class)).thenReturn(question1);
		assertEquals(question1, questionServiceImpl.dtoToEntity(questionDto1));
	}

	@Test
	void testEntityToDto() {
		when(modelMapper.map(question1, QuestionDto.class)).thenReturn(questionDto1);
		assertEquals(questionDto1, questionServiceImpl.entityToDto(question1));
	}

}
