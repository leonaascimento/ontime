package com.ontime.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ontime.dao.ProjectDAO;
import com.ontime.model.Project;
import com.ontime.util.CurrentUser;

@Service
public class ProjectService {
	
	private CurrentUser currentUser;
	private ProjectDAO dao;

	@Autowired
	public ProjectService(ProjectDAO dao, CurrentUser currentUser) {
		this.dao = dao;
		this.currentUser = currentUser;
	}
	
	public List<Project> getList() {
		return dao.getList();
	}
	
	public Project get(int id) {
		return dao.get(id);
	}
	
	@Transactional
	public void add(Project project) {
		project.setCreatedAt(Calendar.getInstance().getTime());
		project.setOwner(currentUser.getUser());
		dao.add(project);
	}
	
	@Transactional
	public void update(Project project) {
		dao.update(project);
	}
	
	@Transactional
	public void remove(Project project) {
		dao.remove(project);
	}
	
}
