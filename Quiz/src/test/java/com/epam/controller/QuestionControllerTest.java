//package com.epam.controller;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.assertj.core.util.Arrays;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//
//import com.epam.model.Question;
//import com.epam.model.Quiz;
//import com.epam.service.QuestionService;
//
//@AutoConfigureMockMvc
//@WebMvcTest(QuestionController.class)
//
// class QuestionControllerTest {
//
//	@MockBean
//	QuestionService questionService;
//
//	@Autowired
//	private MockMvc mockMvc;
//
//	@Test
//	void testGetQuestionLibrary() throws Exception {
//		Quiz quiz = new Quiz();
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
//		
//		List<Question> questionList = new ArrayList<Question>();
//		questionList.add(quest);
//		
//		when(questionService.getQuestionsLibrary()).thenReturn(questionList);
//		
//;		this.mockMvc.perform(get("/question/questionlibrary")).andExpect(view().name("questionlibrary")).andExpect(status().isOk());
//	}
//
//}
