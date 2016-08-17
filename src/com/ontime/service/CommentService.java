package com.ontime.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ontime.dao.CommentDAO;
import com.ontime.messaging.CommentMessaging;
import com.ontime.model.Comment;
import com.ontime.model.Task;
import com.ontime.util.UserLocator;
import com.ontime.viewmodel.CommentSummaryViewModel;

@Service
public class CommentService {

	private CommentDAO dao;
	private UserLocator userLocator;
	private CommentMessaging messaging;

	@Autowired
	public CommentService(CommentDAO dao, UserLocator userLocator, CommentMessaging messaging) {
		this.dao = dao;
		this.userLocator = userLocator;
		this.messaging = messaging;
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
		comment.setCreatedBy(userLocator.getCurrentUser());

		dao.add(comment);

		CommentSummaryViewModel created = new CommentSummaryViewModel(this.get(comment.getId()));

		messaging.broadcastCreated(created);
	}

	@Transactional
	public void add(Comment comment, int taskId) {
		comment.setTask(new Task(taskId));
		this.add(comment);
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
