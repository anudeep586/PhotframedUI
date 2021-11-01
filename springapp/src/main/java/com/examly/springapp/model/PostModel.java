package com.examly.springapp.model;


import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="posts")
public class PostModel {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
    private String imageId;
    
	@Column(name="user_id", nullable = false)
	private String userId;
	
	@Column(name="user_name", nullable = false)
	private String userName;
	
    @Column(nullable = false, length = 40)
    private String imageName;
    
    @Lob
    @Column(length = Integer.MAX_VALUE, nullable = true)
    private byte[] image;
    
    @Column(nullable = true, length = 40)
    private String imageTag;
    
    @Column(nullable = true, length = 250)
    private String imageDescription;
    
	public String getImageDescription() {
		return imageDescription;
	}
	public void setImageDescription(String imageDescription) {
		this.imageDescription = imageDescription;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public PostModel() {
    	super();
    }
    public PostModel(String imageName, String imageTag, byte[] bs, String userId, String userName) {
        	this.imageName = imageName;
        	this.imageTag = imageTag;
			this.image = bs;
			this.userId = userId;
			this.userName = userName;
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

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getImageTag() {
		return imageTag;
	}

	public void setImageTag(String imageTag) {
		this.imageTag = imageTag;
	}

	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public String toString() {
		return "PostModel [imageId=" + imageId + ", imageName=" + imageName + ", image=" + Arrays.toString(image)
				+ ", imageTag=" + imageTag + ", imageDescription=" + imageDescription + "]";
	}
}
