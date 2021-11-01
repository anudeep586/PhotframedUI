package com.examly.springapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.UserModel;
import com.examly.springapp.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public Optional<UserModel> getUser(String userId) {
		Optional<UserModel> user = userRepository.findById(userId);
		return user;
	}
	public boolean existsUser(String userId) {
		if(userRepository.existsById(userId))
			return true;
		else
			return false;
	}
}
