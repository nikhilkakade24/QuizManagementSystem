//package com.epam.quizserviceimpl;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import com.epam.exception.QuizAlreadyExistsException;
//import com.epam.model.Question;
//import com.epam.model.Quiz;
//import com.epam.repository.QuizRepository;
//import com.epam.serviceimpl.QuizServiceImpl;
//
//@ExtendWith(MockitoExtension.class)
//class QuizTest {
//
//	@Mock
//	QuizRepository quizRepository;
//
//	@InjectMocks
//	QuizServiceImpl quizServiceImpl;
//
//	Quiz quiz = new Quiz();
//	List<Question> questionList = new ArrayList<Question>();
//	List<Quiz> quizList = new ArrayList<Quiz>();
//
//	@Test
//	void testSaveQuiz() {
//
//		quiz.setTitle("Python");
//		Question quest = new Question();
//		quest.setTitle("What is Python?");
//		List<String> option = new ArrayList<String>();
//		option.add("Python is programming language");
//		option.add("Python is compiler");
//		option.add("Language");
//		option.add("Snake");
//		quest.setOptions(option);
//		quest.setDifficultyLevel("Easy");
//		quest.setMark(1);
//		quest.setAnswer(1);
//		quest.setQuiz(quiz);
//		questionList.add(quest);
//
//		quiz.setQuestionList(questionList);
//		quiz.setTotalMarks(2);
//		quizList.add(quiz);
//
//		when(quizRepository.save(any(Quiz.class))).thenReturn(quiz);
//
//		assertEquals("Quiz is created", quizServiceImpl.saveQuiz(quiz));
//
//	}
//
//	@Test
//	void testSaveQuizFailure() {
//
//		quiz.setTitle("Python");
//		Question quest = new Question();
//		quest.setTitle("What is Python?");
//		List<String> option = new ArrayList<String>();
//		option.add("Python is programming language");
//		option.add("Python is compiler");
//		option.add("Language");
//		option.add("Snake");
//		quest.setOptions(option);
//		quest.setDifficultyLevel("Easy");
//		quest.setMark(1);
//		quest.setAnswer(1);
//		quest.setQuiz(quiz);
//		questionList.add(quest);
//
//		quiz.setQuestionList(questionList);
//		quiz.setTotalMarks(2);
//		quizList.add(quiz);
//
//		when(quizRepository.findByName("Python")).thenThrow(QuizAlreadyExistsException.class);
//
//		assertThrows(QuizAlreadyExistsException.class, () -> {
//			quizServiceImpl.saveQuiz(quiz);
//		});
//
//	}
//
//	@Test
//	void testGetAllQuiz() {
//
//		quizList = Arrays.asList(new Quiz());
//
//		when(quizRepository.findAll()).thenReturn(quizList);
//
//		List<Quiz> quizzes = quizServiceImpl.getQuizList();
//		assertEquals(1, quizzes.size());
//	}
//
//	@Test
//	void testGetAllQuizFailure() {
//
//		quizList = Arrays.asList();
//
//		when(quizRepository.findAll()).thenReturn(quizList);
//
//		List<Quiz> quizzes = quizServiceImpl.getQuizList();
//		assertEquals(0, quizzes.size());
//	}
//
//	@Test
//	void testDeleteQuiz() {
//		Optional<Quiz> quiz = Optional.ofNullable(new Quiz());
//
//		when(quizRepository.findById(1)).thenReturn(quiz);
//
//		assertEquals("Quiz is deleted", quizServiceImpl.deleteQuiz(1));
//	}
//
//	@Test
//	void testDeleteQuizFailure() {
//		Optional<Quiz> quiz = Optional.ofNullable(null);
//
//		when(quizRepository.findById(1)).thenReturn(quiz);
//
//		assertEquals("Quiz is not deleted", quizServiceImpl.deleteQuiz(1));
//	}
//
//	@Test
//	void testUpdateTitle() {
//
//		Optional<Quiz> quiz = Optional.ofNullable(new Quiz());
//
//		when(quizRepository.findById(1)).thenReturn(quiz);
//
//		assertEquals("Quiz Title is updated", quizServiceImpl.updateTitleQuiz(1, "Adv Java"));
//	}
//
//	@Test
//	void testUpdateTitleFailure() {
//
//		Optional<Quiz> quiz = Optional.ofNullable(null);
//
//		when(quizRepository.findById(1)).thenReturn(quiz);
//
//		assertEquals("Quiz title is not updated", quizServiceImpl.updateTitleQuiz(1, "Adv Java"));
//	}
//
//	@Test
//	void testUpdateQuizMarks() {
//		Optional<Quiz> quiz = Optional.ofNullable(new Quiz());
//		when(quizRepository.findById(1)).thenReturn(quiz);
//		assertEquals("Quiz marks are updated", quizServiceImpl.updateQuizMarks(1, 2));
//	}
//
//	@Test
//	void testUpdateQuizMarksFailure() {
//		Optional<Quiz> quiz = Optional.ofNullable(null);
//		when(quizRepository.findById(1)).thenReturn(quiz);
//		assertEquals("Quiz marks are not updated", quizServiceImpl.updateQuizMarks(1, 2));
//	}
//
//}
