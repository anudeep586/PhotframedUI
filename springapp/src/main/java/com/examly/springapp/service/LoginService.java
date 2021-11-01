package com.examly.springapp.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.LoginModel;
import com.examly.springapp.repository.LoginRepository;

@Service
public class LoginService implements UserDetailsService {

	@Autowired
	private LoginRepository loginRepo;
	

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<LoginModel> check = loginRepo.findById(username);
		if(check.isPresent()){
			return new User(check.get().getEmail(), check.get().getPassword(), new ArrayList<>());
		}
		return null;
	}

}
