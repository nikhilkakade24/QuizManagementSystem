package com.epam.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.dto.AdminDto;
import com.epam.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	AdminService adminService;

	@GetMapping
	public AdminDto adminCrediantials(@RequestBody @Valid AdminDto adminDto) {

		return adminService.validateAdmin(adminDto.getUsername(), adminDto.getPassword());

	}

	@PostMapping
	public ResponseEntity<AdminDto> userRegister(@RequestBody @Valid AdminDto adminDto) {

		return new ResponseEntity<>(adminService.saveAdmin(adminDto), HttpStatus.CREATED);

	}

}
