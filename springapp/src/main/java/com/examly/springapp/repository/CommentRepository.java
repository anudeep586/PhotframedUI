package com.examly.springapp.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.CommentModel;


@Repository
public interface CommentRepository extends CrudRepository<CommentModel, String>{
	public List<CommentModel> getCommentModelByPostId(String postId);
}
