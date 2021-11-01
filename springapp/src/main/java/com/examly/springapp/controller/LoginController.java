package com.examly.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.examly.springapp.model.LoginModel;
import com.examly.springapp.model.UserModel;
import com.examly.springapp.repository.LoginRepository;
import com.examly.springapp.repository.UserRepository;
import com.examly.springapp.response.SuccessUserLoginMessage;
import com.examly.springapp.service.LoginService;


@CrossOrigin
@RestController
public class LoginController {
	@Autowired 
	UserRepository userRepo;
	@Autowired
	LoginRepository loginrepo;
	@Autowired 
	private LoginService loginService;
	@Autowired
	private JwtUtil jwtutil;
	
	@Autowired 
	private AuthenticationManager authenticationManager;

	@PostMapping("/login")
	public @ResponseBody ResponseEntity<?> login(@RequestBody LoginModel user) {
		try {
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
		}catch(UsernameNotFoundException e) {
			e.printStackTrace();
			throw new UsernameNotFoundException("Bad Credentials");
		}
		UserDetails userDetails = this.loginService.loadUserByUsername(user.getEmail());
		String token = this.jwtutil.generateToken(userDetails);
		SuccessUserLoginMessage message = new SuccessUserLoginMessage();
		UserModel usr = userRepo.findByEmail(user.getEmail());
		message.setStatus(200);
		message.setToken(token);
		message.setMessage("User Logged In Successfully");
		message.setUserId(usr.getUsername());
		message.setUserName(usr.getFirstname() + " " + usr.getLastname());
		return new ResponseEntity<>(message, 
				HttpStatus.OK);	
	}	
}