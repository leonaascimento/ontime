package com.ontime.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "projects")
public class Project {
	
	private Integer id;
	private String name;
	private String description;
	private String url;
	private String language;
	private String tags;
	private Date createdAt;
	private User createdBy;
	private List<Task> tasks;
	
	public Project() {
		this.tasks = new ArrayList<Task>();
	}
	
	public Project(int id) {
		this();
		this.setId(id);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@NotBlank
	@Length(max = 255)
	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(max = 255)
	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Length(max = 255)
	@Pattern(regexp = "^$|^http.?://.*$", message = "Deve ser uma url.")
	@Column(name = "url")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	@Length(max = 255)
	@Column(name = "language")
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
	
	@Length(max = 255)
	@Column(name = "tags")
	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	@Column(name = "created_at", nullable = false, updatable = false)
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "created_by_id", nullable = false, updatable = false)
	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	@OneToMany(mappedBy="project", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	
}
