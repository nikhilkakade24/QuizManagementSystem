//package com.epam.controller;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//
//import com.epam.service.AdminService;
//
//@AutoConfigureMockMvc
//@WebMvcTest(AdminController.class)
//class AdminControllerTest {
//	@Autowired
//	private MockMvc mockMvc;
//
//	@MockBean
//	AdminService admiServiceImpl;
//
//	@Test
//	void test_HomePage() throws Exception {
//		this.mockMvc.perform(get("/login/admin/home")).andExpect(view().name("admin")).andExpect(status().isOk());
//	}
//
//	@Test
//	void test_AdminCrediantials() throws Exception {
//		when(admiServiceImpl.validateAdmin("Admin", "Pass@123")).thenReturn(true);
//
//		this.mockMvc.perform(post("/login/admin/submitform").param("username", "Admin").param("password", "Pass@123"))
//				.andExpect(view().name("admindashboard"));
//
//	}
//
//	@Test
//	void testAdminCrediantialsFailure() throws Exception {
//
//		when(admiServiceImpl.validateAdmin("Admin", "Pass@12")).thenReturn(false);
//
//		this.mockMvc.perform(post("/login/admin/submitform").param("username", "Admin").param("password", "Pass@12"))
//				.andExpect(view().name("admindashboard"));
//	}
//
//	@Test
//	void testAdmindashboard() throws Exception {
//
//		this.mockMvc.perform(get("/login/admin/admindashboard")).andExpect(view().name("admindashboard"))
//				.andExpect(status().isOk());
//
//	}
//
//}
