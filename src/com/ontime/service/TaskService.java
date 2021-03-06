package com.ontime.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ontime.dao.TaskDAO;
import com.ontime.model.Project;
import com.ontime.model.TaskStatus;
import com.ontime.model.Task;
import com.ontime.util.UserLocator;

@Service
public class TaskService {
	
	private TaskDAO dao;
	private UserLocator userLocator;

	@Autowired
	public TaskService(TaskDAO dao, UserLocator userLocator) {
		this.dao = dao;
		this.userLocator = userLocator;
	}
	
	public List<Task> getList(int projectId) {
		return dao.getList(projectId);
	}
	
	public List<Task> getList(int projectId, TaskStatus status) {
		return dao.getList(projectId, status);
	}
	
	public Task get(int id) {
		return dao.get(id);
	}
	
	@Transactional
	public void add(Task task) {
		Date now = Calendar.getInstance().getTime();
		
		if (task.getAssignedTo() != null && task.getAssignedTo().getId() == null)
			task.setAssignedTo(null);
		
		if (task.getStatus() == TaskStatus.CLOSED) {
			task.setClosedAt(now);
			task.setClosedBy(userLocator.getCurrentUser());
		}
		
		task.setCreatedAt(now);
		task.setCreatedBy(userLocator.getCurrentUser());

		dao.add(task);
	}
	
	@Transactional
	public void add(Task task, int projectId) {
		task.setProject(new Project(projectId));
		this.add(task);
	}
	
	@Transactional
	public void update(Task task) {
		Date now = Calendar.getInstance().getTime();
		
		if (task.getAssignedTo() != null && task.getAssignedTo().getId() == null)
			task.setAssignedTo(null);
		
		if (task.getStatus() == TaskStatus.CLOSED) {
			Task then = dao.get(task.getId());
			
			if (task.getStatus() != then.getStatus()) {
				task.setClosedAt(now);
				task.setClosedBy(userLocator.getCurrentUser());
			}
		}
		
		dao.update(task);
	}
	
	@Transactional
	public void remove(int id) {
		Task task = this.get(id);
		dao.remove(task);
	}

}
