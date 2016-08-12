package com.ontime.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "tasks")
public class Task {
	
	private Integer id;
	private String title;
	private String description;
	private Status status;
	private Date createdAt;
	private Date closedAt;
	private User createdBy;
	private User assignedTo;
	private User closedBy;
	private Project project;
	private List<Action> actions;
	private List<Comment> comments;

	public Task() {
		this.comments = new ArrayList<Comment>();
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
	@Column(name = "title", nullable = false)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@NotNull
	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.STRING)
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Column(name = "created_at", nullable = false, updatable = false)
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	@Column(name = "closed_at")
	public Date getClosedAt() {
		return closedAt;
	}

	public void setClosedAt(Date closedAt) {
		this.closedAt = closedAt;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by_id", nullable = false, updatable = false)
	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "assigned_to_id")
	public User getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(User assignedTo) {
		this.assignedTo = assignedTo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "closed_by_id")
	public User getClosedBy() {
		return closedBy;
	}

	public void setClosedBy(User closedBy) {
		this.closedBy = closedBy;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id", nullable = false, updatable = false)
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	
	@OneToMany(mappedBy="task", fetch = FetchType.LAZY, orphanRemoval = true)
	public List<Action> getActions() {
		return actions;
	}

	public void setActions(List<Action> actions) {
		this.actions = actions;
	}
	
	@OneToMany(mappedBy="task", fetch = FetchType.LAZY, orphanRemoval = true)
	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
}
