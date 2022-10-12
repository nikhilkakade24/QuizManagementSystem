package com.epam.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.epam.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	

	
	@Query("select u from User u where u.userName= ?1 and u.password= ?2")
	public Optional<User> validateUser(String username, String password);

	public User findByUserName(String username);

}
