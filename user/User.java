package com.aci.payon.rest.aprosewebservices.restfulwebservices.user;

import java.util.Date;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class User {
	
	@Id @GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String id;
	
	@Size(min = 2, message = "Name should have atleast 2 char")
	@ApiModelProperty(notes = "Atleast 2 char")
	private String name;
	
	@Past 
	@ApiModelProperty(notes = "Birth Date should be past")
	private Date dob;
	
	@OneToMany(mappedBy = "user")
	private List<Post> posts;
		
	protected User() {
		
	}
	
	public User(String id, String name, Date dob) {
		super();
		this.id = id;
		this.name = name;
		this.dob = dob;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", dob=" + dob + "]";
	}
	

}
