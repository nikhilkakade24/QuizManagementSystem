package com.epam.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.epam.model.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Integer> {

	@Modifying
	@Query("UPDATE Quiz q SET q.title=:title where q.id=:id")
	public void updateTitle(@Param(value = "title") String title, @Param(value = "id") int id);

	
	@Modifying
	@Query("UPDATE Quiz q SET q.totalMarks=:totalMarks where q.id=:id")
	public void updateMarks(@Param(value = "totalMarks") double totalMarks, @Param(value = "id") int id);
	
	@Query("SELECT title from  Quiz q WHERE q.title=?1")
	public Optional<String> findByName(String title);

	

}
