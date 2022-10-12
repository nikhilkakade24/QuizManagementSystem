package com.epam.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.epam.dto.AdminDto;
import com.epam.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
	
	@Query("select a from Admin a where a.username = ?1 and a.password= ?2")
	public Optional<Admin> validateAdmin(String username, String password);

	

	
	

	

}
