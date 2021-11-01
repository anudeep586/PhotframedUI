package com.examly.springapp.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.PostModel;

@Repository
public interface PostRepository extends CrudRepository<PostModel, String> {
	public List<PostModel> findAllByUserId(String userId);
	public Optional<PostModel> findByImageId(String id);
}
