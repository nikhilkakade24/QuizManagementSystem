package com.epam.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.epam.model.AuthGroup;
import com.epam.model.User;
import com.epam.repository.AuthGroupRepository;
import com.epam.repository.UserRepository;

@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private final AuthGroupRepository authGroupRepository;

	public AppUserDetailsService(UserRepository repository, AuthGroupRepository authGroupRepository) {
		super();
		this.userRepository = repository;
		this.authGroupRepository = authGroupRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(username);
		if (null == user) {
			throw new UsernameNotFoundException("Cannot find username :" + username);
		}

		List<AuthGroup> authorties = authGroupRepository.findByUsername(username);

		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				authorties.stream().map(authGroup -> new SimpleGrantedAuthority(authGroup.getAuthGroup()))
						.collect(Collectors.toList()));

	}

}
