package com.ontime.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ontime.dao.ProjectDAO;
import com.ontime.model.Project;
import com.ontime.util.UserLocator;

@Service
public class ProjectService {
	
	private ProjectDAO dao;
	private UserLocator userLocator;

	@Autowired
	public ProjectService(ProjectDAO dao, UserLocator userLocator) {
		this.dao = dao;
		this.userLocator = userLocator;
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
		project.setCreatedBy(userLocator.getCurrentUser());
		
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
