package com.ontime.viewmodel;

public class UserSummaryView {

	private Integer id;
	private String name;
	private String email;
	
	public UserSummaryView() {
		
	}
	
	public UserSummaryView(Integer id, String name, String email) {
		this();
		this.setId(id);
		this.setName(name);
		this.setEmail(email);
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
