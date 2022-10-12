package com.epam.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.ModelAndView;

import com.epam.dto.QuestionDto;
import com.epam.dto.QuizDto;
import com.epam.dto.UserDto;

@Controller
@RequestMapping("/login/user")
public class UserController {

	@Autowired
	HttpSession httpSession;

	private int questionCount = 0;
	private List<Integer> options = new ArrayList<>();
	private List<QuestionDto> questionList = new ArrayList<>();
	int quizId;

	WebClient webClient;

	@PostConstruct
	public void init() {

		webClient = WebClient.builder().baseUrl("http://localhost:8082")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
	}

	@RequestMapping("/home")
	public String user(Model model) {
		model.addAttribute("var", true);
		return "user";
	}

	@GetMapping("/createuser")
	public String createUser(Model model) {

		return "createuser";
	}

	@PostMapping("/submituser")
	public String submitForm(UserDto userDto, Model model) {

		model.addAttribute("username", userDto.getUserName());

		String jwt = webClient.post().uri("/authenticate").bodyValue(userDto).accept(MediaType.APPLICATION_JSON)
				.retrieve().bodyToMono(String.class).block();

		httpSession.setAttribute("jwt", jwt);

		return "userdashboard";

	}

	@RequestMapping("/takequiz")
	public String takeQuiz(Model model) {

		String jwt = (String) httpSession.getAttribute("jwt");

		List<QuizDto> quizList = webClient.get().uri("/quiz").headers(headers -> headers.setBearerAuth(jwt)).retrieve()
				.bodyToFlux(QuizDto.class).collectList().block();

		model.addAttribute("quizlist", quizList);

		return "userquizdisplay";
	}

	@RequestMapping("/getquiz")
	public ModelAndView getQuestions(@RequestParam("quiz") String quizId) {

		ModelAndView modelAndView = new ModelAndView();

		String jwt = (String) httpSession.getAttribute("jwt");
		questionList = webClient.get().uri("/quiz/questions/" + quizId).headers(headers -> headers.setBearerAuth(jwt))
				.retrieve().bodyToFlux(QuestionDto.class).collectList().block();

		modelAndView.setViewName("redirect:/login/user/getquestion");

		return modelAndView;

	}

	@RequestMapping("/getquestion")
	public ModelAndView chooseQuestion() {

		ModelAndView modelAndView = new ModelAndView();

		if (questionCount < questionList.size()) {
			modelAndView.addObject("question", questionList.get(questionCount));
			modelAndView.setViewName("questiondisplayuser");

		} else {

			String jwt = (String) httpSession.getAttribute("jwt");
			webClient.post().uri("/user/questionlist").bodyValue(questionList)
					.headers(headers -> headers.setBearerAuth(jwt)).retrieve().bodyToMono(Void.class).block();
			long score = webClient.post().uri("/user/result").bodyValue(options)
					.headers(headers -> headers.setBearerAuth(jwt)).retrieve().bodyToMono(Long.class).block();
			modelAndView.addObject("score", score);
			modelAndView.setViewName("showresult");
			questionCount = 0;

		}
		return modelAndView;

	}

	@RequestMapping("/check")
	public String checkOptions(@RequestParam("option") String option) {

		options.add(Integer.parseInt(option));

		questionCount++;

		return "redirect:/login/user/getquestion";

	}

	@RequestMapping("/showsummary")
	public String showSummary(Model model) {

		if (questionCount < questionList.size()) {
			String title = questionList.get(questionCount).getTitle();
			int answer = questionList.get(questionCount).getAnswer();
			int usersAnswer = options.get(questionCount);
			List<String> optionList = questionList.get(questionCount).getOptions();
			model.addAttribute("title", title);
			model.addAttribute("answer", answer);
			model.addAttribute("userAnswer", usersAnswer);
			model.addAttribute("options", optionList);

			questionCount++;
			return "summary";

		} else {
			questionCount = 0;
			options.clear();
			return "userdashboard";
		}

	}

}
