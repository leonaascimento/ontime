package com.ontime.viewmodel;

import java.util.Date;

import com.ontime.model.Comment;

public class CommentSummaryViewModel {

	private int id;
	private int taskId;
	private String content;
	private Date createdAt;
	private UserSummaryViewModel createdBy;
	
	public CommentSummaryViewModel() {
		
	}
	
	public CommentSummaryViewModel(Comment comment) {
		this();
		this.setId(comment.getId());
		this.setTaskId(comment.getTask().getId());
		this.setContent(comment.getContent());
		this.setCreatedAt(comment.getCreatedAt());
		this.setCreatedBy(new UserSummaryViewModel(comment.getCreatedBy()));
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public UserSummaryViewModel getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(UserSummaryViewModel createdBy) {
		this.createdBy = createdBy;
	}

}
