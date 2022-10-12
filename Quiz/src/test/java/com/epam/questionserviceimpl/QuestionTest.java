//package com.epam.questionserviceimpl;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import com.epam.model.Question;
//import com.epam.model.Quiz;
//import com.epam.repository.QuestionRepository;
//import com.epam.serviceimpl.QuestionServiceImpl;
//
//@ExtendWith(MockitoExtension.class)
//class QuestionTest {
//
//	@InjectMocks
//	QuestionServiceImpl questionServiceImpl;
//
//	@Mock
//	QuestionRepository questionRepository;
//
//	Question question1 = new Question("What is java?", Arrays.asList("true", "false"), "easy", 1, 1);
//
//	Question question2 = new Question("Is java is oops language?", Arrays.asList("true", "false"), "easy", 1, 1);
//
//	List<Question> ls = Arrays.asList(question1, question2);
//	List<Question> list = new ArrayList<>(ls);
//
//	Quiz quiz = new Quiz();
//
//	@Test
//	@Order(value = 0)
//	void testQuestionListPresnt() {
//
//		when(questionRepository.findByQuizId(1)).thenReturn(list);
//
//		List<Question> questions = questionServiceImpl.getAllQuestions(1);
//
//		assertEquals(2, questions.size(), "Expected size of questions list");
//	}
//
//	@Test
//
//	void testQuestioListNotPresent() {
//		List<Question> list = Arrays.asList();
//		when(questionRepository.findByQuizId(2)).thenReturn(list);
//
//		List<Question> questions = questionServiceImpl.getAllQuestions(2);
//
//		assertEquals(0, questions.size(), "Expected size of questions list");
//	}
//
//	@Test
//
//	void testDelete() {
//		Optional<Question> question = Optional
//				.ofNullable(new Question("What is java?", Arrays.asList("true", "false"), "easy", 1, 1));
//		when(questionRepository.findById(1)).thenReturn(question);
//
//		assertEquals("Question is deleted", questionServiceImpl.deleteQuestion(1));
//	}
//
//	@Test
//	void testDeleteFailure() {
//		Optional<Question> question = Optional.ofNullable(null);
//		when(questionRepository.findById(1)).thenReturn(question);
//
//		assertEquals("Question not deleted", questionServiceImpl.deleteQuestion(1));
//	}
//
//	@Test
//	void testeUpdateQuestionTitle() {
//		Optional<Question> question = Optional
//				.ofNullable(new Question("What is java?", Arrays.asList("true", "false"), "easy", 1, 1));
//
//		when(questionRepository.findById(1)).thenReturn(question);
//
//		assertEquals("Question title is updated",
//				questionServiceImpl.updateQuestionTitle(1, "is java is platform dependent?"));
//	}
//
//	@Test
//	void testUpdateQuestionTitleFailure() {
//		Optional<Question> question = Optional.ofNullable(null);
//
//		when(questionRepository.findById(1)).thenReturn(question);
//
//		assertEquals("Question title is not updated",
//				questionServiceImpl.updateQuestionTitle(1, "is java is platform dependent?"));
//	}
//
//	@Test
//	void testUpdateQuestionDifficultyLevel() {
//
//		Optional<Question> question = Optional
//				.ofNullable(new Question("What is java?", Arrays.asList("true", "false"), "easy", 1, 1));
//
//		when(questionRepository.findById(1)).thenReturn(question);
//
//		assertEquals("Question difficulty level is updated",
//				questionServiceImpl.updateQuestionDifficultyLevel(1, "medium"));
//	}
//
//	@Test
//	void testUpdateQuestionDifficultyLevelFailure() {
//
//		Optional<Question> question = Optional.ofNullable(null);
//
//		when(questionRepository.findById(1)).thenReturn(question);
//
//		assertEquals("Question difficulty level is not updated",
//				questionServiceImpl.updateQuestionDifficultyLevel(1, "medium"));
//	}
//
//	@Test
//	void testUpdateQuestionMarks() {
//		Optional<Question> question = Optional
//				.ofNullable(new Question("What is java?", Arrays.asList("true", "false"), "easy", 1, 1));
//
//		when(questionRepository.findById(1)).thenReturn(question);
//
//		assertEquals("Question mark is updated", questionServiceImpl.updateQuestionMark(1, 2));
//	}
//
//	@Test
//	void testUpdateQuestionMarksFailure() {
//		Optional<Question> question = Optional.ofNullable(null);
//
//		when(questionRepository.findById(1)).thenReturn(question);
//
//		assertEquals("Question mark is not updated", questionServiceImpl.updateQuestionMark(1, 2));
//	}
//
//	@Test
//	void createQuestionTest() {
//
//		assertTrue(questionServiceImpl.createQuestion("What is java?", Arrays.asList("true", "false"), "easy", 1, 1,
//				quiz) instanceof Question);
//	}
//}
