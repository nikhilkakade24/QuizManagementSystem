package com.epam.serviceimpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.dto.QuestionDto;
import com.epam.dto.UserDto;
import com.epam.exception.InvalidDetailsException;
import com.epam.model.User;
import com.epam.repository.UserRepository;
import com.epam.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	public UserDto addUser(UserDto userDto) {

		User user = dtoToEntity(userDto);

		userRepository.save(user);

		return entityToDto(user);
	}

	public UserDto validateUser(String username, String password) {

		User user = userRepository.validateUser(username, password)
				.orElseThrow(() -> new InvalidDetailsException("InvalidDetails"));

		return entityToDto(user);

	}

	@Override
	public User dtoToEntity(UserDto userDto) {

		return modelMapper.map(userDto, User.class);

	}

	@Override
	public UserDto entityToDto(User user) {

		return modelMapper.map(user, UserDto.class);

	}

	@Override
	public long calculateResult(List<QuestionDto> questionList, List<Integer> userAnswer) {
		long userResult = 0;
		for (int i = 0; i < questionList.size(); i++) {
			if (questionList.get(i).getAnswer() == userAnswer.get(i)) {
				userResult += questionList.get(i).getMark();
			}
		}
		return userResult;
	}

}
