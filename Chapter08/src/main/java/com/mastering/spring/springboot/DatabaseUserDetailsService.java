package com.mastering.spring.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//@Service
public class DatabaseUserDetailsService implements UserDetailsService {

	@Autowired
	// private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// User user = userRepository.findByUsername(username);

//		if (user == null) {
		throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
//		}

//		return new CustomizedUserDetails(user.getId(), user.getUsername(), user.getPassword(), user.getRole());
	}
}