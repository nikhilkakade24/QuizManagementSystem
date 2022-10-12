package com.epam.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.stereotype.Component;

@Entity

public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String title;

	@ElementCollection
	@CollectionTable(name = "options", joinColumns = @JoinColumn(name = "questionID"))
	@LazyCollection(LazyCollectionOption.FALSE)
	
	private List<String> options;

	// @Column(name="difficulty_level")
	private String difficultyLevel;
	private int answer;
	private double mark;

	@ManyToOne(cascade = CascadeType.ALL)
	private Quiz quiz;

	public Question(String title, List<String> options, String difficultyLevel, int answer, double mark) {
		super();
		this.title = title;
		this.options = options;
		this.difficultyLevel = difficultyLevel;
		this.answer = answer;
		this.mark = mark;
	}

	public Question() {
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

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", title=" + title + ", options=" + options + ", difficultyLevel="
				+ difficultyLevel + ", Answer=" + answer + ", mark=" + mark + "]";
	}

	public void removeAllOptions() {
		this.options.clear();
	}

}
