package com.ontime.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ontime.dao.ProjectDAO;
import com.ontime.model.Project;
import com.ontime.model.User;

@Service
public class ProjectService {
	private ProjectDAO dao;

	@Autowired
	public ProjectService(ProjectDAO dao) {
		this.dao = dao;
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
		
		User owner = new User();
		owner.setId(1);
	
		project.setOwner(owner);
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
