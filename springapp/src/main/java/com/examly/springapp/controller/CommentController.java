package com.examly.springapp.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.examly.springapp.model.UserModel;
import com.examly.springapp.response.GetComment;
import com.examly.springapp.response.ResponseMessage;
import com.examly.springapp.service.CommentService;
import com.examly.springapp.service.PostService;
import com.examly.springapp.service.UserService;

@CrossOrigin
@RestController
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	@Autowired 
	private UserService userService;
	@Autowired
	private PostService postService;
	
	@PostMapping("/image/addcomment")
	public ResponseEntity<ResponseMessage> addComment(@RequestBody GetComment getComment){
		String userId = getComment.getUserId();
		ResponseMessage message = new ResponseMessage();
		Optional<UserModel> user = userService.getUser(userId);
		if(user.isEmpty()) {
			message.setMessage("Unauthorized user");
			message.setStatus(400);
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
		String postId = getComment.getPostId();
		String comment = getComment.getComment();
		if(!postService.existsPost(postId)) {
			message.setMessage("Unable to find Post");
			message.setStatus(400);
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
		if(!userService.existsUser(userId)) {
			message.setMessage("Unable to find User");
			message.setStatus(400);
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
		if(commentService.addComment(user.get(), postId, comment)) {
			message.setMessage("Comment added successfully");
			message.setStatus(200);
			return new ResponseEntity<>(message, HttpStatus.OK);
		}else {
			message.setMessage("Unable to add comment");
			message.setStatus(400);
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/image/deletecomment")
	public ResponseEntity<ResponseMessage> deleteComment(@RequestParam String commentId){
		ResponseMessage message = new ResponseMessage();
		if(commentService.deleteComment(commentId)) {
			message.setMessage("Comment deleted Successfully");
			message.setStatus(200);
			return new ResponseEntity<>(message, HttpStatus.OK);
		}
		message.setMessage("Unable to find comment");
		message.setStatus(400);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
	
}
