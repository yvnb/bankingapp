package com.spring.bankingapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.bankingapp.model.UserAccount;
import com.spring.bankingapp.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AccountDetailsService accountDetailsService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.spring.bankingapp.model.User currentUser = userRepository.findByUsername(username);
		if(currentUser == null) {
			throw new UsernameNotFoundException("Please provide valid user name");
		}
		return new User(currentUser.getUsername(), currentUser.getPassword(), new ArrayList<>());
	}
	
	public com.spring.bankingapp.model.User getUser(String username) throws UsernameNotFoundException {
		com.spring.bankingapp.model.User currentUser = userRepository.findByUsername(username);
		if(currentUser == null) {
			throw new UsernameNotFoundException("Please provide valid user name");
		}
		return currentUser;
	}

}
