package com.epam.serviceimpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.dto.AdminDto;
import com.epam.exception.InvalidDetailsException;
import com.epam.model.Admin;
import com.epam.repository.AdminRepository;
import com.epam.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminRepository adminRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public AdminDto saveAdmin(AdminDto adminDto) {
		Admin admin = dtoToEntity(adminDto);

		adminRepository.save(admin);

		return adminDto;

	}

	@Override
	public AdminDto validateAdmin(String username, String password) {

		Admin admin = adminRepository.validateAdmin(username, password)
				.orElseThrow(() -> new InvalidDetailsException("Invalid Details"));

		return entityToDto(admin);
	}

	@Override
	public Admin dtoToEntity(AdminDto adminDto) {

		return modelMapper.map(adminDto, Admin.class);
	}

	@Override
	public AdminDto entityToDto(Admin admin) {

		return modelMapper.map(admin, AdminDto.class);
	}

}