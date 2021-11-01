package com.examly.springapp.response;

public class ImageDetailsResponse {
	private String imageId;
	private String imageName;
	private String imageTag;
	private String imageDescription;
	private String imageUrl;
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getImageTag() {
		return imageTag;
	}

	public void setImageTag(String imageTag) {
		this.imageTag = imageTag;
	}

	public String getImageDescription() {
		return imageDescription;
	}

	public void setImageDescription(String imageDescription) {
		this.imageDescription = imageDescription;
	}
	public ImageDetailsResponse() {
		super();
	}
	
	public ImageDetailsResponse(String imageId, String imageName, String imageTag, String imageDescription,
			String imageUrl) {
		super();
		this.imageId = imageId;
		this.imageName = imageName;
		this.imageTag = imageTag;
		this.imageDescription = imageDescription;
		this.imageUrl = imageUrl;
	}
}
