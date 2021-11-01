package com.examly.springapp.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.PostModel;
import com.examly.springapp.model.UserModel;
import com.examly.springapp.repository.PostRepository;
import com.examly.springapp.response.GetUser;
import com.examly.springapp.response.ResponseMessage;
import com.examly.springapp.service.PostService;
import com.examly.springapp.service.UserService;


@RestController
@CrossOrigin
public class UserController {

	@Autowired 
	private UserService userService;
	
	@Autowired
	private PostService postService;
	
	@RequestMapping(value="/getuser", method = RequestMethod.GET)
	public ResponseEntity<?> getUser(@RequestParam String userId){
		Optional<UserModel> userModel = userService.getUser(userId);
		if(userModel.isPresent()) {
			UserModel user = userModel.get();
			List<PostModel> posts = postService.getAllPostByUser(userId);
			GetUser getUser = new GetUser();
			getUser.setEmail(user.getEmail());
			getUser.setFollowers(user.getFollowers());
			getUser.setFollowing(user.getFollowing());
			getUser.setPost_count(user.getPosts());
			getUser.setUserId(user.getUsername());
			getUser.setUserName(user.getFirstname() + " " + user.getLastname());
			getUser.setPosts(posts);
			return new ResponseEntity <> (getUser, HttpStatus.OK);
		}else {
			ResponseMessage message = new ResponseMessage();
			message.setStatus(400);
			message.setMessage("User Not found");
			return new ResponseEntity <> (message, HttpStatus.BAD_REQUEST);
		}
	}
	
}
