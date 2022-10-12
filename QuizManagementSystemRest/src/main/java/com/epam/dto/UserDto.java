package com.epam.dto;

import javax.validation.constraints.NotBlank;

public class UserDto {

	private int id;
	
	private String firstName;

	private String lastName;
	
	@NotBlank(message= "Username shouldn't be empty")
	private String userName;
	
	@NotBlank(message = "Password should't be empty")
	private String password;

	public UserDto(String firstName, String lastName, String userName, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;

	}

	public UserDto() {

	}

	public int getId() {
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [UserId=" + id + ", FirstName=" + firstName + ", LastName=" + lastName + ", userName=" + userName
				+ ", password=" + password + "]";
	}

}
