package com.ontime.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ontime.dao.ProjectDAO;
import com.ontime.model.Project;
import com.ontime.model.Status;
import com.ontime.model.Task;
import com.ontime.model.User;
import com.ontime.util.UserLocator;

@Service
public class ProjectService {
	
	private ProjectDAO dao;
	private User currentUser;

	@Autowired
	public ProjectService(ProjectDAO dao, UserLocator userLocator) {
		this.dao = dao;
		this.currentUser = userLocator.getCurrentUser();
	}
	
	public List<Project> getList() {
		return dao.getList();
	}
	
	public Project get(int id) {
		return dao.get(id);
	}
	
	@Transactional
	public void add(Project project) {
		Date now = Calendar.getInstance().getTime();
		
		project.setCreatedAt(now);
		project.setCreatedBy(currentUser);
		
		Task task = new Task();
		task.setTitle("__META__");
		task.setStatus(Status.NEW);
		task.setCreatedAt(now);
		task.setCreatedBy(currentUser);
		task.setProject(project);
		
		project.getTasks().add(task);
		
		dao.add(project);
	}
	
	@Transactional
	public void update(Project project) {
		dao.update(project);
	}
	
	@Transactional
	public void remove(int id) {
		Project project = new Project();
		project.setId(id);
		dao.remove(project);
	}
	
}
