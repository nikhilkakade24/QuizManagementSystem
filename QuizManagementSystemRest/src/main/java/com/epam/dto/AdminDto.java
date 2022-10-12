package com.epam.dto;

import javax.validation.constraints.NotBlank;

public class AdminDto {

	private int id;
	
	
	@NotBlank(message= "Username shouldn't be empty")
	private String username;
	
	@NotBlank(message = "Password should't be empty")
	private String password;

	public String getUsername() {
		return username;
	}

	public int getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Admin [Id=" + id + ", username=" + username + ", password=" + password + "]";
	}

}
