package com.epam.dto;

import org.springframework.stereotype.Component;

@Component
public class AdminDto {

	private int id;

	private String username;

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
