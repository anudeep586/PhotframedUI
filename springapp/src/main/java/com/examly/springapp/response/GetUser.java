package com.examly.springapp.response;

import java.util.List;

import com.examly.springapp.model.PostModel;

public class GetUser {
	private String email;
	private String userId;
	private Long post_count;
	private Long followers;
	private Long following;
	private String UserName;
	private List<PostModel> posts;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Long getPost_count() {
		return post_count;
	}
	public void setPost_count(Long post_count) {
		this.post_count = post_count;
	}
	public Long getFollowers() {
		return followers;
	}
	public void setFollowers(Long followers) {
		this.followers = followers;
	}
	public Long getFollowing() {
		return following;
	}
	public void setFollowing(Long following) {
		this.following = following;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public List<PostModel> getPosts() {
		return posts;
	}
	public void setPosts(List<PostModel> posts) {
		this.posts = posts;
	}	
}
