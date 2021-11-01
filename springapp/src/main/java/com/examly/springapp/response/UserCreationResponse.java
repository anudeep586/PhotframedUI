package com.examly.springapp.response;

public class UserCreationResponse {
	private String response;
	private String message;
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "UserCreationResponse [response=" + response + ", message=" + message + "]";
	}
	
}
