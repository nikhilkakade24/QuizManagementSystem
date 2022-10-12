package com.epam.userservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.epam.dto.UserDto;
import com.epam.exception.InvalidDetailsException;
import com.epam.model.User;
import com.epam.repository.UserRepository;
import com.epam.serviceimpl.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
	@InjectMocks
	UserServiceImpl userService;

	@Mock
	UserRepository userRepository;

	@Mock
	ModelMapper modelMapper;

	@Test
	void tsetValidUser() {
		User user = new User();
		user.setUserName("niks");
		user.setPassword("niks@123");

		UserDto userDto = new UserDto();
		user.setUserName("niks");
		user.setPassword("niks@123");

		when(userRepository.validateUser("niks", "niks@123")).thenReturn(Optional.of(user));

		when(userService.entityToDto(user)).thenReturn(userDto);
		assertEquals(userDto, userService.validateUser("niks", "niks@123"));

	}

	@Test
	void tsetInvalidUser() {

		when(userRepository.validateUser("niks", "niks@123")).thenThrow(InvalidDetailsException.class);

		assertThrows(InvalidDetailsException.class, () -> userService.validateUser("niks", "niks@123"));

	}

	@Test
	void testAdddUser() {

		User user = new User();
		user.setUserName("niks");
		user.setPassword("Nikhil@123");

		UserDto userDto = new UserDto();
		userDto.setUserName("niks");
		userDto.setPassword("Nikhil@123");

		when(userService.entityToDto(user)).thenReturn(userDto);
		when(userRepository.save(user)).thenReturn(user);
		when(userService.dtoToEntity(userDto)).thenReturn(user);
		
		assertEquals(userDto, userService.addUser(userDto));

	}
	
	@Test
	void test_dtoTOEntity()
	{	
		User user = new User();
		user.setUserName("niks");
		user.setPassword("Nikhil@123");

		UserDto userDto = new UserDto();
		userDto.setUserName("niks");
		userDto.setPassword("Nikhil@123");
		
		when(modelMapper.map(userDto, User.class)).thenReturn(user);
		assertEquals(user,userService.dtoToEntity(userDto));
	}
	
	@Test
	void test_entityTODto()
	{	
		User user = new User();
		user.setUserName("niks");
		user.setPassword("Nikhil@123");

		UserDto userDto = new UserDto();
		userDto.setUserName("niks");
		userDto.setPassword("Nikhil@123");
		
		when(modelMapper.map(user, UserDto.class)).thenReturn(userDto);
		assertEquals(userDto,userService.entityToDto(user));
	}
	


}
