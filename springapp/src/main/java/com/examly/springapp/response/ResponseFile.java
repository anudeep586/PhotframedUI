package com.examly.springapp.response;

import java.util.List;

import com.examly.springapp.model.CommentModel;

public class ResponseFile {
	private String name;
	private String url;
	private String id;
	private String type;
	private String description;
	private String userId;
	private String userName;
	private List<CommentModel> comments;
	
	
	public List<CommentModel> getComments() {
		return comments;
	}

	public void setComments(List<CommentModel> comments) {
		this.comments = comments;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	public ResponseFile() {
		super();
	}
	public ResponseFile(String name, String url, String type, String Id, String description, String userId, String userName) {
		this.name = name;
		this.url = url;
		this.type = type;
		this.id = Id;
		this.description = description;

		this.userId = userId;
		this.userName = userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
