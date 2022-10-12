package com.epam.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Component
public class QuestionDto {

	private int id;

	private String title;

	private List<String> options;

	private String difficultyLevel;
	private int answer;
	private double mark;
	
	
	//private QuizDto quiz;

	public QuestionDto(String title, List<String> options, String difficultyLevel, int answer, double mark) {
		super();
		this.title = title;
		this.options = options;
		this.difficultyLevel = difficultyLevel;
		this.answer = answer;
		this.mark = mark;
	}

	public QuestionDto() {
		super();
	
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getOptions() {
		return options;
	}

	public void setOptions(List<String> options) {
		this.options = options;
	}

	public String getDifficultyLevel() {
		return difficultyLevel;
	}

	public void setDifficultyLevel(String difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}

	public int getAnswer() {
		return answer;
	}

	public void setAnswer(int answer) {
		this.answer = answer;
	}

	public double getMark() {
		return mark;
	}

	public void setMark(double mark) {
		this.mark = mark;
	}

//	public QuizDto getQuiz() {
//		return quiz;
//	}
//
//	public void setQuiz(QuizDto quiz) {
//		this.quiz = quiz;
//	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", title=" + title + ", options=" + options + ", difficultyLevel="
				+ difficultyLevel + ", Answer=" + answer + ", mark=" + mark + "]";
	}

	public void removeAllOptions() {
		this.options.clear();
	}

}
