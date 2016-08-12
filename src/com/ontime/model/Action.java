package com.ontime.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "actions")
public class Action {
	
	private Integer id;
	private String description;
	private Boolean done;
	private Date doneAt;
	private Date createdAt;
	private User doneBy;
	private User createdBy;
	private Task task;

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
	@Column(name = "description", nullable = false)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@NotNull
	@Column(name = "done", nullable = false)
	public Boolean getDone() {
		return done;
	}

	public void setDone(Boolean done) {
		this.done = done;
	}

	@Column(name = "done_at")
	public Date getDoneAt() {
		return doneAt;
	}

	public void setDoneAt(Date doneAt) {
		this.doneAt = doneAt;
	}

	@Column(name = "created_at", nullable = false, updatable = false)
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "done_by_id")
	public User getDoneBy() {
		return doneBy;
	}

	public void setDoneBy(User doneBy) {
		this.doneBy = doneBy;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by_id")
	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "task_id")
	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}
	
}
