package com.examly.springapp.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.examly.springapp.model.PostModel;
import com.examly.springapp.model.UserModel;
import com.examly.springapp.repository.PostRepository;
import com.examly.springapp.response.ImageDetailsResponse;
import com.examly.springapp.response.ResponseFile;
import com.examly.springapp.response.ResponseMessage;
import com.examly.springapp.service.CommentService;
import com.examly.springapp.service.LoginService;
import com.examly.springapp.service.PostService;
import com.examly.springapp.service.UserService;

@CrossOrigin
@RestController
public class PostController {
	@Value("${uploadDir}")
	private String uploadFolder;
	@Autowired
	private PostService postService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private PostRepository postRepo;

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/image/{id}")
	public @ResponseBody ResponseEntity<?> imageDetails(@PathVariable String id){
		Optional<PostModel> fileDB = postRepo.findById(id);
		if(fileDB.isEmpty()) {
			ResponseMessage msg = new ResponseMessage();
			msg.setMessage("Broken Url");
			msg.setStatus(400);
		return ResponseEntity.badRequest().body(msg);
		}else {
			ResponseFile response = new ResponseFile();
			PostModel file = fileDB.get();
			response.setDescription(file.getImageDescription());
			response.setId(file.getImageId());
			response.setName(file.getImageName());
			response.setUrl("http://localhost:8080/files/"+file.getImageId());
			response.setType(file.getImageTag());
			response.setUserId(file.getUserId());
			response.setUserName(file.getUserName());
			response.setComments(commentService.getComment(file.getImageId()));
			return ResponseEntity.ok().body(response);
		}
	}

	@RequestMapping(value = "/addImage", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<ResponseMessage> createPost(HttpServletRequest request,
			@RequestParam("file") MultipartFile file, @RequestParam("description") String description, @RequestParam("userid") String userId) {
		ResponseMessage response = new ResponseMessage();
		Optional<UserModel> user = userService.getUser(userId);
		if(user.isEmpty()) {
			response.setStatus(400);
			response.setMessage("Unauthorized Request");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			if(file.isEmpty()) {
				response.setStatus(400);
				response.setMessage("Select a Image to post");
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
			if (!file.getContentType().startsWith("image")) {
				response.setMessage("Sorry! Invalid File Type");
				response.setStatus(400);
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
			if(file.getSize() > 1024000L) {
				response.setMessage("Sorry! File Size is Large");
				response.setStatus(400);
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
				
			String uploadDirectory = request.getServletContext().getRealPath(uploadFolder);
			log.info("uploadDirectory:: " + uploadDirectory);
			String fileName = file.getOriginalFilename();
			String filePath = Paths.get(uploadDirectory, fileName).toString();
			log.info("FileName: " + file.getOriginalFilename());
			if (fileName == null || fileName.contains("..")) {
				response.setMessage("Sorry! Filename contains invalid path sequence");
				response.setStatus(400);
				return new ResponseEntity<>(response,
						HttpStatus.BAD_REQUEST);
			}
			try {
				File dir = new File(uploadDirectory);
				if (!dir.exists()) {
					log.info("Folder Created");
					dir.mkdirs();
				}
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
				stream.write(file.getBytes());
				stream.close();
			} catch (Exception e) {
				log.info("in catch");
				e.printStackTrace();
				response.setStatus(400);
				response.setMessage("Error in Saving File");
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
			String username = user.get().getFirstname() +" "+ user.get().getLastname();
			postService.store(file, description, userId, username);
			response.setStatus(200);
			response.setMessage("Post Uploaded Successfully");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception: " + e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/files/{id}")
	public ResponseEntity<?> getFile(@PathVariable String id) {
		ResponseMessage response = new ResponseMessage();
		try {
		PostModel fileDB = postService.getFile(id);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getImageName() + "\"")
				.body(fileDB.getImage());
		}catch(Exception e) {
			response.setMessage("Broken Url");
			response.setStatus(400);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/post/deletePost/id/{imageId}")
	public @ResponseBody ResponseEntity<ResponseMessage> showProductDetails(@PathVariable String imageId) {
		ResponseMessage msg = new ResponseMessage();
		try {
		postService.deletePostbyId(imageId);
		}catch(Exception e) {
			msg.setMessage("Broken Url");
			msg.setStatus(400);
			return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
		}
		msg.setMessage("Post Deleted Successfully");
		msg.setStatus(200);
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}

	@GetMapping("/image")
	public ResponseEntity<List<ResponseFile>> getListFiles() {
		try {
			Iterable<PostModel> files = postService.getAllFiles();
			List<ResponseFile> response = new ArrayList<ResponseFile>();
			for(PostModel file : files) {
				String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/")
						.path(file.getImageId().toString()).toUriString();
				ResponseFile responseFile = new ResponseFile(file.getImageName(), fileDownloadUri, file.getImageTag(),
						file.getImageId(), file.getImageDescription(), file.getUserId(), file.getUserName());
				responseFile.setComments(commentService.getComment(file.getImageId()));
				response.add(responseFile);
			}
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
}