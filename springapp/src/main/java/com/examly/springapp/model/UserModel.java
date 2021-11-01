package com.examly.springapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="users")
public class UserModel{
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String username;
	
    @Column(nullable = false, unique = true, length = 60)
    private String email;
    
    @Column(nullable = false, length = 60)
    private String firstname;
    
    @Column(nullable = true, length = 60)
    private String lastname;
    
    @Column(nullable = true, columnDefinition="bigint(10) DEFAULT 0")
    private Long posts;
    
    @Column(nullable = true, columnDefinition="bigint(10)")
    private Long followers;
    
    @Column(nullable = true, columnDefinition="bigint(10) DEFAULT 0")
    private Long following;
    
    @Column(nullable = true, columnDefinition="BOOLEAN DEFAULT false")
    private boolean active;
    
    @Column(nullable = true, columnDefinition="char(15) DEFAULT '0'")
    private String mobileNumber;

    @Column(nullable = true, columnDefinition="char(15) DEFAULT 'user'")
    private String role;
    

	public String getEmail(){
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}
	
    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Long getPosts() {
		return posts;
	}

	public void setPosts(Long posts) {
		this.posts = posts;
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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserModel [username=" + username + ", email=" + email + ", firstname="
				+ firstname + ", lastname=" + lastname + ", posts=" + posts + ", followers=" + followers
				+ ", following=" + following + ", active=" + active + ", mobileNumber=" + mobileNumber + ", role="
				+ role + "]";
	}

}
