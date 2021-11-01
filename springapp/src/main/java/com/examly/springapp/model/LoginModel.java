package com.examly.springapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="login")
public class LoginModel {
	@Id
    @Column(nullable = false, unique = true, length = 60)
    private String email;
    
    @Column(nullable = false, length = 40)
    private String password;

	@Override
	public String toString() {
		return "LoginModel [email=" + email + ", password=" + password + "]";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
