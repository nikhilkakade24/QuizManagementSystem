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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;

import com.epam.dto.QuestionDto;
import com.epam.dto.QuizDto;

@RequestMapping("/quiz")
@Controller
public class QuizController {

	WebClient webClient;

	@PostConstruct
	public void init() {
		webClient = WebClient.builder().baseUrl("http://localhost:8082")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
	}

	@Autowired
	HttpSession httpSession;

	static final String QUIZLIST = "quizlist";

	@RequestMapping(path = "/getquiz")
	public String getQuiz(Model model) {

		String jwt = (String) httpSession.getAttribute("jwt");

		List<QuizDto> quizList = webClient.get().uri("/quiz").headers(headers -> headers.setBearerAuth(jwt)).retrieve()
				.bodyToFlux(QuizDto.class).collectList().block();

		model.addAttribute(QUIZLIST, quizList);

		return "quizdisplay";

	}

	@RequestMapping(path = "/getquizfordeletion")
	public String getQuizList(Model model) {

		String jwt = (String) httpSession.getAttribute("jwt");

		List<QuizDto> quizList = webClient.get().uri("/quiz").headers(headers -> headers.setBearerAuth(jwt)).retrieve()
				.bodyToFlux(QuizDto.class).collectList().block();

		model.addAttribute(QUIZLIST, quizList);

		return "quizdisplayfordeletion";

	}

	@RequestMapping("/getquestions")
	public String getQuestions(@RequestParam("quiz") String quizId, Model model) {

		String jwt = (String) httpSession.getAttribute("jwt");
		List<QuestionDto> questions = webClient.get().uri("/quiz/questions/" + quizId)
				.headers(headers -> headers.setBearerAuth(jwt)).retrieve().bodyToFlux(QuestionDto.class).collectList()
				.block();

		model.addAttribute("questionlist", questions);

		return "questiondisplay";

	}

	@RequestMapping("/getquestionsfordeletion")
	public String getQuestionForDeletion(@RequestParam("quiz") String quizId, Model model) {

		String jwt = (String) httpSession.getAttribute("jwt");
		List<QuestionDto> questions = webClient.get().uri("/quiz/questions/" + quizId)
				.headers(headers -> headers.setBearerAuth(jwt)).retrieve().bodyToFlux(QuestionDto.class).collectList()
				.block();
		model.addAttribute("questionlist", questions);

		return "questiondisplayfordeletion";

	}

	@RequestMapping("/quizlist")
	public String quizList(Model model) {

		String jwt = (String) httpSession.getAttribute("jwt");

		List<QuizDto> quizList = webClient.get().uri("/quiz").headers(headers -> headers.setBearerAuth(jwt)).retrieve()
				.bodyToFlux(QuizDto.class).collectList().block();

		model.addAttribute("quiz", quizList);
		return QUIZLIST;
	}

	@RequestMapping("/changename")
	public String changeName(@RequestParam("id") String id, Model model) {

		String jwt = (String) httpSession.getAttribute("jwt");
		String name = webClient.get().uri("/quiz/quizname/" + id).headers(headers -> headers.setBearerAuth(jwt))
				.retrieve().bodyToMono(String.class).block();

		model.addAttribute("name", name);
		model.addAttribute("id", id);
		return "updatename";

	}

	@RequestMapping("/updatedname")
	public String updatedName(@RequestParam("id") String id, @RequestParam("newname") String newName, Model model) {

		String jwt = (String) httpSession.getAttribute("jwt");
		webClient.put().uri("/quiz/" + id + "/" + newName).headers(headers -> headers.setBearerAuth(jwt)).retrieve()
				.bodyToMono(QuizDto.class).block();

		List<QuizDto> quizList = webClient.get().uri("/quiz").headers(headers -> headers.setBearerAuth(jwt)).retrieve()
				.bodyToFlux(QuizDto.class).collectList().block();

		model.addAttribute("quiz", quizList);
		return QUIZLIST;

	}

	@RequestMapping("/displayquizfordeletion")
	public String deleteQuiz(QuizDto quizDto, @RequestParam("id") int id, Model model) {

		String jwt = (String) httpSession.getAttribute("jwt");
		String name = webClient.get().uri("/quiz/quizname/" + id).headers(headers -> headers.setBearerAuth(jwt))
				.retrieve().bodyToMono(String.class).block();

		model.addAttribute("name", name);
		model.addAttribute("id", id);
		return "deletequiz";

	}

	@RequestMapping("/deletequiz")
	public String quizDisplayAfterDelete(@RequestParam("id") int id, Model model) {

		String jwt = (String) httpSession.getAttribute("jwt");
		webClient.delete().uri("/quiz/" + id).headers(headers -> headers.setBearerAuth(jwt)).retrieve()
				.bodyToMono(QuizDto.class).block();

		List<QuizDto> quizList = webClient.get().uri("/quiz").headers(headers -> headers.setBearerAuth(jwt)).retrieve()
				.bodyToFlux(QuizDto.class).collectList().block();

		model.addAttribute("quiz", quizList);
		return QUIZLIST;

	}

	@PostMapping("/deletequestions")
	public String deleteQuestion(@RequestParam("question") int questionId, Model model) {

		String jwt = (String) httpSession.getAttribute("jwt");

		webClient.delete().uri("/question/" + questionId).headers(headers -> headers.setBearerAuth(jwt)).retrieve()
				.bodyToMono(QuestionDto.class).block();

		return "admindashboard";

	}

	@RequestMapping(path = "/create")
	public String createQuiz(Model model) {

		return "createquiz";

	}

	String quizTitle;
	int numberOfQuestions;
	int marksOfQuiz;

	@Autowired
	QuizDto quiz;

	@RequestMapping(path = "/submitquiz")
	public String submitQuiz(QuizDto quizDto, @RequestParam("number") String numberOfQuestions, Model model) {

		this.numberOfQuestions = Integer.parseInt(numberOfQuestions);

		quiz.setTitle(quizDto.getTitle());
		quiz.setTotalMarks(quizDto.getTotalMarks());
		model.addAttribute(numberOfQuestions);
		return "createquestion";

	}

	int count = 0;
	@Autowired
	QuestionDto createdQuestion;

	List<QuestionDto> questionList = new ArrayList<>();

	@RequestMapping(path = "/submitquestion")
	public String submitQuestion(QuestionDto question, Model model) {

		questionList.add(question);
		count++;
		if (count < this.numberOfQuestions) {

			return "createquestion";
		} else {

			quiz.setQuestionList(questionList);

			String jwt = (String) httpSession.getAttribute("jwt");

			webClient.post().uri("/quiz").bodyValue(quiz).headers(headers -> headers.setBearerAuth(jwt))
					.accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(QuizDto.class).block();

			count = 0;
			questionList.clear();
			return "quizsubmition";
		}

	}

}
