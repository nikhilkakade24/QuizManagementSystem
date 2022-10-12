package com.epam.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

import com.epam.dto.AdminDto;
import com.epam.exception.InvalidDetailsException;
import com.epam.service.AdminService;
import com.epam.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@SpringBootTest
class AdminControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	AdminService admiServiceImpl;

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
	void test_validateAdmin() throws Exception {

		objectMapper = new ObjectMapper();

		AdminDto adminDto = new AdminDto();
		adminDto.setUsername("Admin");
		adminDto.setPassword("Pass@123");

		String str = objectMapper.writeValueAsString(adminDto);

		when(admiServiceImpl.validateAdmin(adminDto.getUsername(), adminDto.getPassword())).thenReturn(adminDto);

		mockMvc.perform(get("/admin").header("Authorization", "Bearer " + jwt).contentType(MediaType.APPLICATION_JSON).content(str)).andExpect(status().isOk());
	}

	@Test
	void test_validate_Admin_Failure() throws Exception {

		objectMapper = new ObjectMapper();

		AdminDto adminDto = new AdminDto();
		adminDto.setUsername("Admin");
		adminDto.setPassword("Pass@12");

		String str = objectMapper.writeValueAsString(adminDto);

		when(admiServiceImpl.validateAdmin(adminDto.getUsername(), adminDto.getPassword())).thenThrow(InvalidDetailsException.class);

		mockMvc.perform(get("/admin").header("Authorization", "Bearer " + jwt).contentType(MediaType.APPLICATION_JSON).content(str)).andExpect(status().isUnauthorized());
	}

	@Test
	void test_admin_Register() throws Exception {

		objectMapper = new ObjectMapper();

		AdminDto adminDto = new AdminDto();
		adminDto.setUsername("Admin");
		adminDto.setPassword("Pass@12");

		String str = objectMapper.writeValueAsString(adminDto);

		when(admiServiceImpl.saveAdmin(adminDto)).thenReturn(adminDto);

		mockMvc.perform(post("/admin").header("Authorization", "Bearer " + jwt).contentType(MediaType.APPLICATION_JSON).content(str))
				.andExpect(status().isCreated());

	}
	
	@Test
	void test_admin_Invalid_Register() throws Exception {

		objectMapper = new ObjectMapper();

		AdminDto adminDto = new AdminDto();
		adminDto.setUsername("");
		adminDto.setPassword("Pass@12");

		String str = objectMapper.writeValueAsString(adminDto);		

		mockMvc.perform(post("/admin").header("Authorization", "Bearer " + jwt).contentType(MediaType.APPLICATION_JSON).content(str))
				.andExpect(status().isBadRequest());

	}
	

	

}