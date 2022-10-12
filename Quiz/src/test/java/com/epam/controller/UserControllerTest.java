//package com.epam.controller;
//
//import static org.mockito.Mockito.doThrow;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//
//import com.epam.dto.UserDto;
//import com.epam.exception.CrediantialsEmptyException;
//import com.epam.exception.InvalidDetailsException;
//import com.epam.model.User;
//import com.epam.service.QuestionService;
//import com.epam.service.UserService;
//
//@AutoConfigureMockMvc
//@SpringBootTest
//
//class UserControllerTest {
//
//	@Autowired
//	private MockMvc mockMvc;
//
//	@MockBean
//	UserService userService;
//
//	@MockBean
//	QuestionService questionService;
//
//	@Test
//	void userTest() throws Exception {
//		this.mockMvc.perform(get("/login/user/home")).andExpect(view().name("user")).andExpect(status().isOk());
//	}
//
//	@Test
//	void test_userRegister_success() throws Exception {
//		UserDto userDto = new UserDto();
//		userDto.setFirstName("Pratham");
//		userDto.setLastName("Raut");
//		userDto.setUserName("raut");
//		userDto.setPassword("raut@123");
//
//		User user = new User();
//		user.setFirstName("Pratham");
//		user.setLastName("Raut");
//		user.setUserName("raut");
//		user.setPassword("raut@123");
//
//		when(userService.dtoToEntity(userDto)).thenReturn(user);
//
//		when(userService.addUser(user)).thenReturn(true);
//
//		this.mockMvc.perform(post("/login/user/registeruser")).andExpect(view().name("user"))
//				.andExpect(status().isOk());
//	}
//
//	@Test
//	void test_userRegister_failure() throws Exception {
//		UserDto userDto = new UserDto();
//		userDto.setFirstName("Pratham");
//		userDto.setLastName("Raut");
//		userDto.setUserName("");
//		userDto.setPassword("raut@123");
//
//		User user = new User();
//		user.setFirstName("Pratham");
//		user.setLastName("Raut");
//		user.setUserName("");
//		user.setPassword("raut@123");
//
//		when(userService.dtoToEntity(userDto)).thenReturn(user);
//
//		doThrow(CrediantialsEmptyException.class).when(userService).addUser(user);
//
//		this.mockMvc.perform(post("/login/user/registeruser")).andExpect(view().name("user"))
//				.andExpect(status().isOk());
//	}
//
//	@Test
//	void test_submitForm_success() throws Exception {
//		when(userService.validateUser("niks", "niks@123")).thenReturn(true);
//
//		this.mockMvc.perform(post("/login/user/submituser").param("userName", "niks").param("password", "niks@123"))
//				.andExpect(view().name("userdashboard"));
//
//	}
//	
//	@Test
//	void test_submitForm_failure() throws Exception {
//		when(userService.validateUser("nik", "niks@12")).thenReturn(false);
//		
//		doThrow(InvalidDetailsException.class).when(userService).validateUser("nik", "niks@12");
//		
//		this.mockMvc.perform(post("/login/user/submituser").param("userName", "nik").param("password", "niks@12"))
//				.andExpect(view().name("error"));
//
//	}
//	@Test
//	void takeQuizTest() throws Exception {
//		this.mockMvc.perform(get("/login/user/takequiz")).andExpect(view().name("userquizdisplay"))
//				.andExpect(status().isOk());
//	}
//
//	@Test
//	void getQuestionsTest() throws Exception {
//		this.mockMvc.perform(get("/login/user/getquiz").param("quiz", "3"))
//				.andExpect(view().name("redirect:/login/user/getquestion"));
//	}
//
//	@Test
//	void test_chooseQuestion() throws Exception {
//		this.mockMvc.perform(get("/login/user/getquestion")).andExpect(view().name("showresult"));
//	}
//
//	@Test
//	void test_CheckOptions() throws Exception {
//		this.mockMvc.perform(get("/login/user/check").param("option", "1"))
//				.andExpect(view().name("redirect:/login/user/getquestion"));
//	}
//	
//	@Test
//	void test_createUser() throws Exception
//	{
//		this.mockMvc.perform(get("/login/user/createuser")).andExpect(view().name("createuser")).andExpect(status().isOk());
//	}
//}
