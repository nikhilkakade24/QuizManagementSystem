package com.epam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.epam.dto.UserDto;
import com.epam.serviceimpl.AppUserDetailsService;
import com.epam.util.JwtUtil;



@RestController
public class AuthApi {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private AppUserDetailsService userDetailsService;

	@PostMapping("/authenticate")
	public String createAuthenticationToken(@RequestBody UserDto authenticationRequest)
			throws Exception {
		
		
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUserName(), authenticationRequest.getPassword()));
			
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
			
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUserName());

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return jwt;
	}
}