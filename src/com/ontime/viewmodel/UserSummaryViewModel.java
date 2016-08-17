package com.ontime.viewmodel;

import com.ontime.model.User;

public class UserSummaryViewModel {

	private Integer id;
	private String name;
	private String email;
	
	public UserSummaryViewModel() {
		
	}
	
	public UserSummaryViewModel(Integer id, String name, String email) {
		this();
		this.setId(id);
		this.setName(name);
		this.setEmail(email);
	}
	
	public UserSummaryViewModel(User user) {
		this();
		this.setId(user.getId());
		this.setName(user.getName());
		this.setEmail(user.getEmail());
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

}
