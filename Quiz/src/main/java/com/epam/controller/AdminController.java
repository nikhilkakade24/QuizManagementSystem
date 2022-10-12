package com.epam.controller;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.WebClient;

import com.epam.dto.UserDto;

@Controller
@RequestMapping("/login/admin")
public class AdminController {

	@Autowired
	HttpSession httpSession;

	WebClient webClient;

	@PostConstruct
	public void init() {
		webClient = WebClient.builder().baseUrl("http://localhost:8082")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
	}

	@RequestMapping("/home")
	public String home(Model model) {

		return "admin";
	}

	@PostMapping("/submitform")
	public String adminCrediantials(UserDto userDto, Model model) {

		model.addAttribute("username", userDto.getUserName());

		String jwt = webClient.post().uri("/authenticate").bodyValue(userDto).accept(MediaType.APPLICATION_JSON)
				.retrieve().bodyToMono(String.class).block();

		httpSession.setAttribute("jwt", jwt);

		return "admindashboard";

	}

	@RequestMapping("/admindashboard")
	public String adminDashboard() {
		return "admindashboard";
	}

}
