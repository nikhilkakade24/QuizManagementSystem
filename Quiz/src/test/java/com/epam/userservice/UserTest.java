//package com.epam.userservice;
//
//import static org.junit.jupiter.api.Assertions.assertAll;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
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
//import com.epam.exception.CrediantialsEmptyException;
//import com.epam.exception.InvalidDetailsException;
//import com.epam.model.Question;
//import com.epam.model.Quiz;
//import com.epam.model.User;
//import com.epam.repository.UserRepository;
//import com.epam.serviceimpl.UserServiceImpl;
//
//@ExtendWith(MockitoExtension.class)
//class UserTest {
//	@InjectMocks
//	UserServiceImpl userService;
//
//	@Mock
//	UserRepository userRepository;
//
//	@Test
//	void tsetValidUser() {
//		User user = new User();
//		user.setUserName("niks");
//		user.setPassword("niks@123");
//
//		when(userRepository.validateUser("niks", "niks@123")).thenReturn(Optional.of(user));
//
//		assertEquals(true, userService.validateUser("niks", "niks@123"));
//
//	}
//
//	@Test
//	void tsetInvalidUser() {
//
//		when(userRepository.validateUser("User", "Pass@123")).thenReturn(Optional.empty());
//
//		assertThrows(InvalidDetailsException.class, () -> userService.validateUser("User", "Pass@123"));
//
//	}
//
//	@Test
//	void testAdddUser() {
//		User user = new User();
//
//		user.setFirstName("Nikhil");
//		user.setLastName("Kakade");
//		user.setUserName("niks");
//		user.setPassword("Nikhil@123");
//
//		when(userRepository.save(user)).thenReturn(user);
//
//		assertEquals(true, userService.addUser(user));
//
//	}
//
//	@Test
//	void testAddUserFailure() {
//		User user = new User();
//
//		user.setFirstName("Nikhil");
//		user.setLastName("Kakade");
//		user.setUserName("niks");
//		user.setPassword("");
//
//		assertThrows(CrediantialsEmptyException.class, () -> userService.addUser(user));
//	}
//
//	@Test
//	void testCalculateResult() {
//		List<Question> questionList = new ArrayList<Question>();
//		Question question1 = new Question();
//		Quiz quiz1 = new Quiz();
//		quiz1.setTitle("Java");
//		question1.setTitle("Who invented Java Programming?");
//		List<String> options1 = new ArrayList<String>();
//		options1.add("Guido van Rossum");
//		options1.add("James Gosling");
//		options1.add("James Gosling");
//		options1.add("Bjarne Stroustrup");
//		question1.setOptions(options1);
//		question1.setDifficultyLevel("Easy");
//		question1.setMark(1);
//		question1.setAnswer(2);
//		question1.setQuiz(quiz1);
//		questionList.add(question1);
//
//		List<Integer> answers = Arrays.asList(2);
//
//		assertEquals(1, userService.calculateResult(questionList, answers));
//	}
//
//}
