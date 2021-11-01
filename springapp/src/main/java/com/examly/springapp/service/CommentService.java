package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.CommentModel;
import com.examly.springapp.model.UserModel;
import com.examly.springapp.repository.CommentRepository;
import com.examly.springapp.repository.PostRepository;
import com.examly.springapp.repository.UserRepository;

@Service
public class CommentService {
	@Autowired 
	private CommentRepository commentRepository;
	
	public List<CommentModel> getComment(String postId){
		return commentRepository.getCommentModelByPostId(postId);
	}
	public boolean addComment(UserModel userModel, String postId, String comment) {
		CommentModel commentModel = new CommentModel();
		commentModel.setComment(comment);
		commentModel.setPostId(postId);
		commentModel.setUserModel(userModel);
		try {
			commentRepository.save(commentModel);
		}catch(Exception e) {
			return false;
		}
		return true;
	}
	public boolean deleteComment(String commentId) {
		Optional<CommentModel> comment = commentRepository.findById(commentId);
		if(comment.isPresent()) {
			commentRepository.deleteById(commentId);
			return true;
		}
		return false;
	}
	public boolean updateComment(String userId, String postId, String comment) {
		return false;
	}
	
}
