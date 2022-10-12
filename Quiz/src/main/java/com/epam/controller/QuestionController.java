package com.epam.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.WebClient;

import com.epam.dto.QuestionDto;

@Controller
@RequestMapping("/question")
public class QuestionController {

	

	WebClient webClient;

	@PostConstruct
	public void init() {
		webClient = WebClient.builder().baseUrl("http://localhost:8082")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
	}

	@Autowired
	HttpSession httpSession;

	@RequestMapping("/questionlibrary")
	public String getQuestionLibrary(Model model) {
	

		String jwt = (String) httpSession.getAttribute("jwt");

		List<QuestionDto> quest = webClient.get().uri("/question").headers(headers -> headers.setBearerAuth(jwt))
				.retrieve().bodyToFlux(QuestionDto.class).collectList().block();
	

		model.addAttribute("questions", quest);
		return "questionlibrary";

	}

}
