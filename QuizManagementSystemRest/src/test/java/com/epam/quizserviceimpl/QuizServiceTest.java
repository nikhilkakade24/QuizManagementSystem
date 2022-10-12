package com.epam.quizserviceimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
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
import com.epam.dto.QuizDto;
import com.epam.exception.InvalidQuizException;
import com.epam.model.Question;
import com.epam.model.Quiz;
import com.epam.repository.QuizRepository;
import com.epam.serviceimpl.QuizServiceImpl;

@ExtendWith(MockitoExtension.class)
class QuizServiceTest {

	@Mock
	QuizRepository quizRepository;

	@InjectMocks
	QuizServiceImpl quizServiceImpl;

	@Mock
	ModelMapper modelMapper;

	Quiz quiz = new Quiz();
	QuizDto quizDto = new QuizDto();
	
	List<Question> questionList = new ArrayList<>();
	List<QuestionDto> questionDtoList = new ArrayList<>();
	List<Quiz> quizList = new ArrayList<Quiz>();
	List<QuizDto> quizDtoList = new ArrayList<>();
	@BeforeEach
	void init() {

		quiz.setTitle("Java");
		questionList.add(new Question("What is java", Arrays.asList("true", "false"), "easy", 1, 1));
		quiz.setQuestionList(questionList);
		quiz.setTotalMarks(1);
		
		quizDto.setTitle("Java");
		questionDtoList.add(new QuestionDto("What is java", Arrays.asList("true", "false"), "easy", 1, 1));
		quizDto.setQuestionList(questionDtoList);
		quizDto.setTotalMarks(1);

	}

	@Test
	void test_Save_Quiz() {

		when(quizServiceImpl.dtoToEntity(quizDto)).thenReturn(quiz);

		when(quizRepository.save(any(Quiz.class))).thenReturn(quiz);

		when(quizServiceImpl.entityToDto(quiz)).thenReturn(quizDto);

		assertEquals(quizDto, quizServiceImpl.saveQuiz(quizDto));

	}

	@Test
	void testGetAllQuiz() {

		quizList = Arrays.asList(new Quiz());

		when(quizRepository.findAll()).thenReturn(quizList);

		assertEquals(1, quizServiceImpl.getQuizList().size());
	}

	@Test
	void testDeleteQuiz() {

		when(quizRepository.findById(1)).thenReturn(Optional.of(quiz));

		doNothing().when(quizRepository).deleteById(1);

		quizRepository.deleteById(1);

		verify(quizRepository).deleteById(1);

		when(quizServiceImpl.entityToDto(this.quiz)).thenReturn(quizDto);

		assertEquals(quizDto, quizServiceImpl.deleteQuiz(1));
	}

	@Test
	void testDeleteQuizFailure() {

		when(quizRepository.findById(1)).thenThrow(InvalidQuizException.class);

		assertThrows(InvalidQuizException.class, () -> quizServiceImpl.deleteQuiz(1));
	}

	@Test
	void testUpdateTitle() {

		when(quizRepository.findById(1)).thenReturn(Optional.of(quiz));

		when(quizRepository.save(quiz)).thenReturn(quiz);

		when(quizServiceImpl.entityToDto(this.quiz)).thenReturn(quizDto);

		assertEquals(quizDto, quizServiceImpl.updateTitleQuiz(1, "Adv Java"));

	}

	@Test
	void testUpdateTitleFailure() {

		when(quizRepository.findById(1)).thenThrow(InvalidQuizException.class);

		assertThrows(InvalidQuizException.class, () -> quizServiceImpl.updateTitleQuiz(1, "Adv Java"));

	}

	@Test
	void testUpdateQuizMarks() {

		when(quizRepository.findById(1)).thenReturn(Optional.of(quiz));

		when(quizRepository.save(quiz)).thenReturn(quiz);

		when(quizServiceImpl.entityToDto(this.quiz)).thenReturn(quizDto);

		assertEquals(quizDto, quizServiceImpl.updateQuizMarks(1, 2));
	}

	@Test
	void testUpdateQuizMarksFailure() {
		when(quizRepository.findById(1)).thenThrow(InvalidQuizException.class);

		assertThrows(InvalidQuizException.class, () -> quizServiceImpl.updateQuizMarks(1, 2));
	}

	@Test
	void testGetQuizName() {

		when(quizRepository.findById(1)).thenReturn(Optional.of(quiz));
		assertEquals("Java", quizServiceImpl.getQuizName(1));
	}

	@Test
	void testGetQuizNameFailure() {

		when(quizRepository.findById(1)).thenThrow(InvalidQuizException.class);
		assertThrows(InvalidQuizException.class, () -> quizServiceImpl.getQuizName(1));
	}
	
	@Test
	void testDtoToEntity() {

		when(modelMapper.map(quizDto, Quiz.class)).thenReturn(quiz);
		assertEquals(quiz, quizServiceImpl.dtoToEntity(quizDto));
	}
	
	@Test
	void testEntityToDto() {

		when(modelMapper.map(quiz, QuizDto.class)).thenReturn(quizDto);
		assertEquals(quizDto, quizServiceImpl.entityToDto(quiz));
	}

}
