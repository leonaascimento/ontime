package com.ontime.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.ontime.model.Project;

@Repository
public class ProjectDAO {
	
	@PersistenceContext
	private EntityManager context;

	public List<Project> getList() {
		String criteria = "select p from Project p";
		return context.createQuery(criteria, Project.class).getResultList();
	}

	public Project get(int id) {
		String criteria = "select p from Project p where p.id = :id";
		return context.createQuery(criteria, Project.class).setParameter("id", id).getSingleResult();
	}

	public void add(Project project) {
		context.persist(project);
	}

	public void update(Project project) {
		context.persist(project);
	}

	public void remove(Project project) {
		context.remove(project);
	}
	
}
