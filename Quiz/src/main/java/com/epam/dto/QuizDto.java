package com.epam.dto;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class QuizDto {

	private int id;
	private String title;

	private List<QuestionDto> questionList;

	private double totalMarks;

	public QuizDto(String title, double totalMarks) {
		super();
		this.title = title;
		this.totalMarks = totalMarks;
	}

	public QuizDto() {
		super();

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<QuestionDto> getQuestionList() {
		return questionList;
	}
//
	public void setQuestionList(List<QuestionDto> questionList) {

		this.questionList = questionList;
	}

	public double getTotalMarks() {
		return totalMarks;
	}

	public void setTotalMarks(double totalMarks) {
		this.totalMarks = totalMarks;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Quiz [id=" + id + ", title=" + title + ", questionList=" + questionList + ", totalMarks=" + totalMarks
				+ "]";
	}

}
