package com.ontime.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ontime.dao.CommentDAO;
import com.ontime.model.Comment;
import com.ontime.model.User;
import com.ontime.util.UserLocator;

@Service
public class CommentService {
	
	private CommentDAO dao;
	private User currentUser;

	@Autowired
	public CommentService(CommentDAO dao, UserLocator userLocator) {
		this.dao = dao;
		this.currentUser = userLocator.getCurrentUser();
	}
	
	public List<Comment> getList(int taskId) {
		return dao.getList(taskId);
	}
	
	public Comment get(int id) {
		return dao.get(id);
	}
	
	@Transactional
	public void add(Comment comment) {
		Date now = Calendar.getInstance().getTime();
		
		comment.setCreatedAt(now);
		comment.setCreatedBy(currentUser);
		
		dao.add(comment);
	}
	
	@Transactional
	public void update(Comment comment) {
		dao.update(comment);
	}
	
	@Transactional
	public void remove(int id) {
		dao.remove(new Comment(id));
	}
	
}
