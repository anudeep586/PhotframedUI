package com.examly.springapp.service;

import com.examly.springapp.model.PostModel;
import com.examly.springapp.repository.PostRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PostService implements UserDetailsService {

	@Autowired
	private PostRepository postRepo;

	public boolean existsPost(String postId) {
		if(postRepo.existsById(postId))
			return true;
		return false;
	}
	public PostModel store(MultipartFile file, String description, String userId, String username) throws IOException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		PostModel FileDB = new PostModel(fileName, file.getContentType(), file.getBytes(), userId, username);
		FileDB.setImageDescription(description);
		return postRepo.save(FileDB);
	}
	public void deletePostbyId(String id){
		postRepo.deleteById(id);
	}
	
	public PostModel getFile(String id) {
		return postRepo.findById(id).get();
	}
	public Iterable<PostModel> getAllFiles() {
		return postRepo.findAll();
	}
	
	public List<PostModel> getAllPostByUser(String userId){
		return postRepo.findAllByUserId(userId);
	}
	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		Optional<PostModel> check = postRepo.findById(id);
		if (check != null) {
			return new User(check.get().getImageId().toString(), check.get().getImageName(), new ArrayList<>());
		}
		return null;
	}
}