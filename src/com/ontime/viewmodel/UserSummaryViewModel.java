package com.ontime.viewmodel;

import com.ontime.model.User;

public class UserSummaryViewModel {

	private Integer id;
	private String name;
	private String email;
	private String github;
	private String imageUrl;
	
	public UserSummaryViewModel() {
		
	}

	public UserSummaryViewModel(User user) {
		this();
		this.setId(user.getId());
		this.setName(user.getName());
		this.setEmail(user.getEmail());
		this.setGithub(user.getGithub());
		this.setImageUrl(user.getImageUrl());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGithub() {
		return github;
	}

	public void setGithub(String github) {
		this.github = github;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
