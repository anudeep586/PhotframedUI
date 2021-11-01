package com.examly.springapp.response;

public class ResponseMessage {
	  private String message;
	  private int status;
	  public ResponseMessage() {
		  super();
	  }
	  public ResponseMessage(String message, int status) {
		  this.message = message;
		  this.setStatus(status);
	  }
	  public ResponseMessage(String message) {
	    this.message = message;
	  }

	  public String getMessage() {
	    return message;
	  }

	  public void setMessage(String message) {
	    this.message = message;
	  }
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

}