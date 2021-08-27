package com.aci.payon.rest.aprosewebservices.restfulwebservices.user;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Post {
	
	@Id @GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String postId;
	
	private String description;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JsonIgnore
	private User user;
	
	public Post() {
		super();
	}

	public Post(String postId, String description, User user) {
		super();
		this.postId = postId;
		this.description = description;
		this.user = user;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	

}
