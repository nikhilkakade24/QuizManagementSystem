package com.epam.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;

import com.epam.dto.QuestionDto;
import com.epam.dto.QuizDto;
import com.epam.exception.InvalidQuizException;
import com.epam.service.QuestionService;
import com.epam.service.QuizService;
import com.epam.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@SpringBootTest
class QuizControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	QuizService quizService;

	@MockBean
	QuestionService questionService;

	ObjectMapper objectMapper;

	@Autowired
	private JwtUtil jwtUtil;

	private String jwt;

	@BeforeEach
	void initialize() {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ADMIN"));
		UserDetails userDetails = new User("niks", "niks@123", authorities);
		jwt = jwtUtil.generateToken(userDetails);

		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				userDetails.getUsername(), null, userDetails.getAuthorities());

		SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

	}

	@Test
	void test_submit_Quiz() throws Exception {
		objectMapper = new ObjectMapper();

		QuizDto quizDto = new QuizDto();
		quizDto.setTitle("Java");
		quizDto.setTotalMarks(1);

		QuestionDto questionDto = new QuestionDto();
		questionDto.setTitle("What is java?");

		List<QuestionDto> questionList = new ArrayList<>();
		questionList.add(questionDto);

		quizDto.setQuestionList(questionList);

		String str = objectMapper.writeValueAsString(quizDto);

		when(quizService.saveQuiz(quizDto)).thenReturn(quizDto);

		mockMvc.perform(post("/quiz").header("Authorization", "Bearer " + jwt).content(str)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	@Test
	void test_get_Quiz_Library() throws Exception {

		QuizDto quizDto = new QuizDto();
		quizDto.setTitle("Java");
		quizDto.setTotalMarks(1);

		QuestionDto questionDto = new QuestionDto();
		questionDto.setTitle("What is java?");

		List<QuestionDto> questionList = new ArrayList<>();
		questionList.add(questionDto);

		quizDto.setQuestionList(questionList);

		List<QuizDto> quizList = new ArrayList<>();
		quizList.add(quizDto);

		when(quizService.getQuizList()).thenReturn(quizList);

		mockMvc.perform(get("/quiz").header("Authorization", "Bearer " + jwt).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}

	@Test
	void test_get_Questions() throws Exception {

		QuizDto quizDto = new QuizDto();
		quizDto.setTitle("Java");
		quizDto.setTotalMarks(1);

		QuestionDto questionDto = new QuestionDto();
		questionDto.setTitle("What is java?");

		List<QuestionDto> questionList = new ArrayList<>();
		questionList.add(questionDto);

		quizDto.setQuestionList(questionList);

		List<QuizDto> quizList = new ArrayList<>();
		quizList.add(quizDto);

		when(questionService.getAllQuestions(1)).thenReturn(questionList);

		mockMvc.perform(get("/quiz/questions/1").header("Authorization", "Bearer " + jwt)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	@Test
	void test_get_Questions_Failure() throws Exception {

		QuizDto quizDto = new QuizDto();
		quizDto.setTitle("Java");
		quizDto.setTotalMarks(1);

		QuestionDto questionDto = new QuestionDto();
		questionDto.setTitle("What is java?");

		List<QuestionDto> questionList = new ArrayList<>();
		questionList.add(questionDto);

		quizDto.setQuestionList(questionList);

		List<QuizDto> quizList = new ArrayList<>();
		quizList.add(quizDto);

		when(questionService.getAllQuestions(1)).thenThrow(InvalidQuizException.class);

		mockMvc.perform(get("/quiz/questions/1").header("Authorization", "Bearer " + jwt)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().is4xxClientError());

	}

	@Test
	void test_updateQuizName_Sucess() throws Exception {

		QuizDto quizDto = new QuizDto();
		quizDto.setTitle("Java");
		quizDto.setTotalMarks(1);

		QuestionDto questionDto = new QuestionDto();
		questionDto.setTitle("What is java?");

		List<QuestionDto> questionList = new ArrayList<>();
		questionList.add(questionDto);

		quizDto.setQuestionList(questionList);

		when(quizService.updateTitleQuiz(1, "Adv Java")).thenReturn(quizDto);

		mockMvc.perform(put("/quiz/1/Adv Java").header("Authorization", "Bearer " + jwt)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	@Test
	void test_updateQuizName_Failure() throws Exception {

		when(quizService.updateTitleQuiz(1, "Adv Java")).thenThrow(InvalidQuizException.class);

		mockMvc.perform(put("/quiz/1/Adv Java").header("Authorization", "Bearer " + jwt)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().is4xxClientError());

	}

	@Test
	void test_deleteQuiz() throws Exception {

		QuizDto quizDto = new QuizDto();
		quizDto.setTitle("Java");
		quizDto.setTotalMarks(1);

		QuestionDto questionDto = new QuestionDto();
		questionDto.setTitle("What is java?");

		List<QuestionDto> questionList = new ArrayList<>();
		questionList.add(questionDto);

		quizDto.setQuestionList(questionList);

		when(quizService.deleteQuiz(1)).thenReturn(quizDto);

		mockMvc.perform(
				delete("/quiz/1").header("Authorization", "Bearer " + jwt).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}

	@Test
	void test_deleteQuiz_Failure() throws Exception {

		when(quizService.deleteQuiz(1)).thenThrow(InvalidQuizException.class);

		mockMvc.perform(
				delete("/quiz/1").header("Authorization", "Bearer " + jwt).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError());

	}

	@Test
	void test_getQuizName() throws Exception {

		when(quizService.getQuizName(any(Integer.class))).thenReturn("java");

		mockMvc.perform(get("/quiz/quizname/1").header("Authorization", "Bearer " + jwt)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

}
