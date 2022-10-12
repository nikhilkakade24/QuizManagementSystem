//package com.epam.controller;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
//
//import java.util.ArrayList;
//import java.util.List;
//
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
//import com.epam.service.QuizService;
//
//@AutoConfigureMockMvc
//@SpringBootTest
//
//class QuizControllerTest {
//
//	@Autowired
//	private MockMvc mockMvc;
//
//	@MockBean
//	QuizService quizService;
//
//	@MockBean
//	QuestionService questionService;
//
//	Quiz quiz = new Quiz();
//	List<Question> questionList = new ArrayList<Question>();
//	List<Quiz> quizList = new ArrayList<Quiz>();
//
//	@Test
//	void test_GetQuiz() throws Exception {
//		quiz.setTitle("Python");
//		Question quest = new Question();
//		quest.setTitle("What is Python?");
//		List<String> option = new ArrayList<String>();
//		option.add("Python is programming language");
//		option.add("Python is compiler");
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
//		when(quizService.getQuizList()).thenReturn(quizList);
//
//		this.mockMvc.perform(get("/quiz/getquiz")).andExpect(view().name("quizdisplay")).andExpect(status().isOk());
//	}
//
//	@Test
//	void test_GetQuizList() throws Exception {
//
//		when(quizService.getQuizList()).thenReturn(quizList);
//		this.mockMvc.perform(get("/quiz/getquiz1")).andExpect(view().name("quizdisplay1")).andExpect(status().isOk());
//	}
//
//	@Test
//	void test_GetQuestions() throws Exception {
//		Question quest = new Question();
//		quest.setTitle("What is Python?");
//		List<String> option = new ArrayList<String>();
//		option.add("Python is programming language");
//		option.add("Python is compiler");
//		quest.setOptions(option);
//		quest.setQuiz(quiz);
//		questionList.add(quest);
//
//		when(questionService.getAllQuestions(1)).thenReturn(questionList);
//		this.mockMvc.perform(get("/quiz/getquestions").param("quiz", "1")).andExpect(view().name("questiondisplay"));
//
//	}
//
//	@Test
//	void test_GetQuestionsForDeletion() throws Exception {
//
//		Question quest = new Question();
//		quest.setTitle("What is Python?");
//		List<String> option = new ArrayList<String>();
//		option.add("Python is programming language");
//		option.add("Python is compiler");
//		quest.setOptions(option);
//		quest.setQuiz(quiz);
//		questionList.add(quest);
//
//		when(questionService.getAllQuestions(1)).thenReturn(questionList);
//		this.mockMvc.perform(get("/quiz/getquestions1").param("quiz", "1")).andExpect(view().name("questiondisplay1"));
//
//	}
//
//	@Test
//	void test_QuizList() throws Exception {
//		when(quizService.getQuizList()).thenReturn(quizList);
//		this.mockMvc.perform(get("/quiz/quizlist")).andExpect(view().name("quizlist")).andExpect(status().isOk());
//
//	}
//
//	@Test
//	void test_ChangeName() throws Exception {
//
//		this.mockMvc.perform(get("/quiz/changename").param("id", "1")).andExpect(view().name("updatename"));
//
//	}
//
//	@Test
//	void test_UpdatedName() throws Exception {
//		when(quizService.updateTitleQuiz(1, "Py")).thenReturn("Quiz title is updated");
//		this.mockMvc.perform(get("/quiz/changename1").param("id", "1").param("newname", "Py"))
//				.andExpect(view().name("quizlist"));
//
//	}
//
//	@Test
//	void test_DeleteQuiz() throws Exception {
//		when(quizService.getQuizName(1)).thenReturn("Py");
//		this.mockMvc.perform(get("/quiz/deletequiz").param("id", "1")).andExpect(view().name("deletequiz"));
//
//	}
//
//	@Test
//	void test_QuizDisplayAfterDelete() throws Exception {
//		when(quizService.getQuizList()).thenReturn(quizList);
//		this.mockMvc.perform(get("/quiz/deletequiz1").param("id", "1")).andExpect(view().name("quizlist"));
//
//	}
//
//	@Test
//	void test_DeleteQuestion() throws Exception {
//		when(questionService.deleteQuestion(1)).thenReturn("Question is deleted");
//		this.mockMvc.perform(post("/quiz/deletequestions").param("question", "1"))
//				.andExpect(view().name("admindashboard"));
//
//	}
//	
//	@Test
//	void test_createQuiz() throws Exception {
//		
//		this.mockMvc.perform(get("/quiz/create")).andExpect(view().name("createquiz"));
//
//	}
//	
//	
//	
//
//}
