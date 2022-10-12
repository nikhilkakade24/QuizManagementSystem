package com.epam.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import com.epam.exception.InvalidQuestionException;
import com.epam.service.QuestionService;
import com.epam.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@SpringBootTest
class QuestionControllerTest {
	@Autowired
	private MockMvc mockMvc;

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
	void test_get_Question_Library() throws Exception {

		QuestionDto questionDto = new QuestionDto();
		questionDto.setTitle("What is java?");

		List<QuestionDto> questionLibrary = new ArrayList<>();
		questionLibrary.add(questionDto);

		when(questionService.getQuestionsLibrary()).thenReturn(questionLibrary);

		mockMvc.perform(
				get("/question").header("Authorization", "Bearer " + jwt).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}

	@Test
	void test_get_Question_Sucess() throws Exception {

		QuestionDto questionDto = new QuestionDto();
		questionDto.setTitle("What is java?");

		when(questionService.getQuestion(1)).thenReturn(questionDto);

		mockMvc.perform(get("/question/1").header("Authorization", "Bearer " + jwt).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	@Test
	void test_get_Question_Failure() throws Exception {

		when(questionService.getQuestion(1)).thenThrow(InvalidQuestionException.class);

		mockMvc.perform(get("/question/1").header("Authorization", "Bearer " + jwt).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError());

	}

	@Test
	void test_delete_Question_Sucess() throws Exception {
		QuestionDto questionDto = new QuestionDto();
		questionDto.setTitle("What is java?");

		when(questionService.deleteQuestion(1)).thenReturn(questionDto);

		mockMvc.perform(delete("/question/1").header("Authorization", "Bearer " + jwt).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	@Test
	void test_delete_Question_Failure() throws Exception {

		when(questionService.deleteQuestion(1)).thenThrow(InvalidQuestionException.class);

		mockMvc.perform(delete("/question/1").header("Authorization", "Bearer " + jwt).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError());

	}

	@Test
	void test_updateTitle_Question_Sucess() throws Exception {

		QuestionDto questionDto = new QuestionDto();
		questionDto.setTitle("What is java?");

		when(questionService.updateQuestionTitle(1, "what is py?")).thenReturn(questionDto);

		mockMvc.perform(put("/question/title/1/what is py?").header("Authorization", "Bearer " + jwt).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}

	@Test
	void test_updateTitle_Question_Failure() throws Exception {

		QuestionDto questionDto = new QuestionDto();
		questionDto.setTitle("What is py?");

		when(questionService.updateQuestionTitle(1, "what is py?")).thenThrow(InvalidQuestionException.class);

		mockMvc.perform(put("/question/title/1/what is py?").header("Authorization", "Bearer " + jwt).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}

	@Test
	void test_updateDifficultyLevel_Question_Sucess() throws Exception {

		QuestionDto questionDto = new QuestionDto();
		questionDto.setDifficultyLevel("easy");
		when(questionService.updateQuestionDifficultyLevel(1, "medium")).thenReturn(questionDto);

		mockMvc.perform(put("/question/difficulty/1/medium").header("Authorization", "Bearer " + jwt).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}

	@Test
	void test_updateDifficultyLevel_Question_Failure() throws Exception {

		QuestionDto questionDto = new QuestionDto();
		questionDto.setDifficultyLevel("easy");
		when(questionService.updateQuestionDifficultyLevel(1, "medium")).thenThrow(InvalidQuestionException.class);

		mockMvc.perform(put("/question/difficulty/1/medium").header("Authorization", "Bearer " + jwt).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError());

	}

	@Test
	void test_updateMark_Question_Sucess() throws Exception {

		QuestionDto questionDto = new QuestionDto();
		questionDto.setMark(1);
		when(questionService.updateQuestionMark(1, 2)).thenReturn(questionDto);

		mockMvc.perform(put("/question/mark/1/2").header("Authorization", "Bearer " + jwt).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	@Test
	void test_updateMark_Question_Failure() throws Exception {

		QuestionDto questionDto = new QuestionDto();
		questionDto.setMark(1);
		when(questionService.updateQuestionMark(1, 2)).thenThrow(InvalidQuestionException.class);

		mockMvc.perform(put("/question/mark/1/2").header("Authorization", "Bearer " + jwt).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError());

	}
}
