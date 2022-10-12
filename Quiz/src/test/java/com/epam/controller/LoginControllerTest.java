//package com.epam.controller;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.web.servlet.MockMvc;
//
//@WebMvcTest(LoginController.class)
//@AutoConfigureMockMvc
//class LoginControllerTest {
//
//	@Autowired
//	private MockMvc mockMvc;
//
//	@Test
//	void testLoginPage() throws Exception {
//		this.mockMvc.perform(get("/login")).andExpect(view().name("login")).andExpect(status().isOk());
//	}
//	
//	@Test
//	void testLoginPageFailure() throws Exception {
//		this.mockMvc.perform(get("/login1")).andExpect(status().is4xxClientError());
//	}
//
//}
