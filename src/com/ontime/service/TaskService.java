package com.ontime.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ontime.dao.TaskDAO;
import com.ontime.model.Project;
import com.ontime.model.Status;
import com.ontime.model.Task;
import com.ontime.model.User;
import com.ontime.util.UserLocator;

@Service
public class TaskService {
	
	private TaskDAO dao;
	private User currentUser;

	@Autowired
	public TaskService(TaskDAO dao, UserLocator userLocator) {
		this.dao = dao;
		this.currentUser = userLocator.getCurrentUser();
	}
	
	public List<Task> getList(int projectId) {
		return dao.getList(projectId);
	}
	
	public Task get(int id) {
		return dao.get(id);
	}
	
	@Transactional
	public void add(Task task) {
		Date now = Calendar.getInstance().getTime();
		
		if (task.getAssignedTo() != null && task.getAssignedTo().getId() == null)
			task.setAssignedTo(null);
		
		if (task.getStatus() == Status.CLOSED) {
			task.setClosedAt(now);
			task.setClosedBy(currentUser);
		}
		
		task.setCreatedAt(now);
		task.setCreatedBy(currentUser);

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
		
		if (task.getStatus() == Status.CLOSED) {
			Task then = dao.get(task.getId());
			
			if (task.getStatus() != then.getStatus()) {
				task.setClosedAt(now);
				task.setClosedBy(currentUser);
			}
		}
		
		dao.update(task);
	}
	
	@Transactional
	public void remove(int id) {
		dao.remove(new Task(id));
	}

}
