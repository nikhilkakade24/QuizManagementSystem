package com.epam.service;

import java.util.List;

import com.epam.dto.QuestionDto;
import com.epam.dto.UserDto;
import com.epam.model.Question;
import com.epam.model.User;

public interface UserService {
	public UserDto addUser(UserDto userDto);

	public UserDto validateUser(String username, String password);
	
	public long calculateResult(List<QuestionDto> questionList, List<Integer> userAnswer);

	User dtoToEntity(UserDto userDto);

	UserDto entityToDto(User user);

}
