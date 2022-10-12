package com.epam.adminserviceimpl;

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

import com.epam.dto.AdminDto;
import com.epam.exception.InvalidDetailsException;
import com.epam.model.Admin;
import com.epam.repository.AdminRepository;
import com.epam.serviceimpl.AdminServiceImpl;

@ExtendWith(MockitoExtension.class)
class AdminServiceTest {

	@Mock
	AdminRepository adminRepository;

	@InjectMocks
	AdminServiceImpl adminService;

	@Mock
	ModelMapper modelMapper;

	@Test
	void test_Valid_Admin() {

		AdminDto adminDto = new AdminDto();
		adminDto.setUsername("Admin");
		adminDto.setPassword("Pass@123");

		Admin admin = new Admin();
		admin.setUsername("Admin");
		admin.setPassword("Pass@123");

		when(adminService.entityToDto(admin)).thenReturn(adminDto);
		when(adminRepository.validateAdmin("Admin", "Pass@123")).thenReturn(Optional.of(admin));

		assertEquals(adminDto, adminService.validateAdmin("Admin", "Pass@123"));

	}

	@Test
	void test_Invalid_Admin() {

		when(adminRepository.validateAdmin("Admin", "Pass@12")).thenThrow(InvalidDetailsException.class);

		assertThrows(InvalidDetailsException.class, () -> adminService.validateAdmin("Admin", "Pass@12"));

	}

	@Test
	void test_Register_Admin() {

		AdminDto adminDto = new AdminDto();
		adminDto.setUsername("Admin");
		adminDto.setPassword("Pass@123");

		Admin admin = new Admin();
		admin.setUsername("Admin");
		admin.setPassword("Pass@123");

		when(adminService.dtoToEntity(adminDto)).thenReturn(admin);

		when(adminRepository.save(admin)).thenReturn(admin);

		assertEquals(adminDto, adminService.saveAdmin(adminDto));
	}

}
