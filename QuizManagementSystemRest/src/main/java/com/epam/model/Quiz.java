package com.epam.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.stereotype.Component;



@Entity

public class Quiz {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;

	@OneToMany(mappedBy="quiz", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Question> questionList;
	

	private double totalMarks;

	public Quiz(String title, double totalMarks) {
		super();
		this.title = title;
		this.totalMarks = totalMarks;
	}

	public Quiz() {
		super();
		
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Question> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<Question> questionList) {
//		questionList.forEach((q)->q.setQuiz(this));
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
