package com.epam.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
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

import com.epam.dto.UserDto;
import com.epam.service.UserService;
import com.epam.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	UserService userService;

	ObjectMapper objectMapper;

	@Autowired
	private JwtUtil jwtUtil;

	private String jwt;

	@BeforeEach
	void initialize() {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("USER"));
		UserDetails userDetails = new User("ani", "ani@123", authorities);
		jwt = jwtUtil.generateToken(userDetails);

		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				userDetails.getUsername(), null, userDetails.getAuthorities());

		SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

	}

	@Test
	void test_registerUser_Sucess() throws Exception {

		objectMapper = new ObjectMapper();

		UserDto userDto = new UserDto();
		userDto.setUserName("niks");
		userDto.setPassword("niks@123");

		String str = objectMapper.writeValueAsString(userDto);

		when(userService.addUser(any(UserDto.class))).thenReturn(userDto);

		mockMvc.perform(post("/user").header("Authorization", "Bearer " + jwt).contentType(MediaType.APPLICATION_JSON)
				.content(str)).andExpect(status().isOk());

	}

	@Test
	void test_registerUser_Failure() throws Exception {

		objectMapper = new ObjectMapper();

		UserDto userDto = new UserDto();
		userDto.setUserName("");
		userDto.setPassword("niks@123");

		String str = objectMapper.writeValueAsString(userDto);

		mockMvc.perform(post("/user").header("Authorization", "Bearer " + jwt).contentType(MediaType.APPLICATION_JSON)
				.content(str)).andExpect(status().isBadRequest());

	}

}
