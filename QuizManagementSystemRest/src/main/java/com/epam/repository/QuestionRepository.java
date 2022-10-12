package com.epam.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.epam.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Integer>{
	
	@Query("select q from Question q where quiz.id = ?1 ")
	 public Optional<List<Question>> findByQuizId(int quizid);
	
	
	@Modifying
	@Query("UPDATE Question q SET q.title=:title where q.id=:id")
	public Question updateQuestiontitle(@Param(value = "id") int id, @Param(value="title")String title);
	
	
	@Modifying
	@Query("DELETE Question q where q.id = ?1")
	public void deleteByQuestionId(int id);
	

	@Modifying
	@Query("UPDATE Question q SET q.difficultyLevel=:difficultyLevel where q.id=:id")
	public Question updateQuestionDifficultyLevel(@Param(value = "id") int id, @Param(value="difficultyLevel")String difficultyLevel);
	

	@Modifying
	@Query("UPDATE Question q SET q.mark=:mark where q.id=:id")
	public void updateQuestionMark(int id, double mark);

	
	

}