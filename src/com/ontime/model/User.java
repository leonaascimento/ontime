package com.ontime.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User {
	
	private Integer id;
	private String name;
	private String email;
	private String password;
	private String github;
	private String imageUrl;
	private List<Project> projects;
	private List<Task> createdTasks;
	private List<Task> assignedTasks;
	private List<Task> closedTasks;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "name")
	@NotBlank
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "email")
	@NotBlank
	@Email
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "password")
	@NotBlank
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "github")
	@Pattern(regexp = "^$|^https://github.com/[^/]*$", message = "Deve ser um usuário do GitHub.")
	public String getGithub() {
		return github;
	}

	public void setGithub(String github) {
		this.github = github;
	}
	
	@Column(name = "image_url")
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	@OneToMany(mappedBy="createdBy", fetch = FetchType.LAZY)
	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	
	@OneToMany(mappedBy="createdBy", fetch = FetchType.LAZY)
	public List<Task> getCreatedTasks() {
		return createdTasks;
	}

	public void setCreatedTasks(List<Task> createdTasks) {
		this.createdTasks = createdTasks;
	}
	
	@OneToMany(mappedBy="assignedTo", fetch = FetchType.LAZY)
	public List<Task> getAssignedTasks() {
		return assignedTasks;
	}

	public void setAssignedTasks(List<Task> assignedTasks) {
		this.assignedTasks = assignedTasks;
	}
	
	@OneToMany(mappedBy="closedBy", fetch = FetchType.LAZY)
	public List<Task> getClosedTasks() {
		return closedTasks;
	}

	public void setClosedTasks(List<Task> closedTasks) {
		this.closedTasks = closedTasks;
	}
	
}
