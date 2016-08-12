package com.ontime.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.ontime.model.Project;
import com.ontime.model.Task;

@Repository
public class ProjectDAO {
	
	@PersistenceContext
	private EntityManager em;

	public List<Project> getList() {
		String criteria = "select p from Project p";
		return em.createQuery(criteria, Project.class).getResultList();
	}

	public Project get(int id) {
		String projectCriteria = "select p from Project p where p.id = :id";
		Project project = em.createQuery(projectCriteria, Project.class)
				.setParameter("id", id)
				.getSingleResult();
		
		String tasksCriteria = "select t from Task t where t.project.id = :projectId and t.title != :title";
		List<Task> tasks = em.createQuery(tasksCriteria, Task.class)
				.setParameter("projectId", id)
				.setParameter("title", "__META__")
				.getResultList();
		
		project.setTasks(tasks);
		
		return project;
	}

	public void add(Project project) {
		em.persist(project);
	}

	public void update(Project project) {
		em.persist(em.contains(project) ? project : em.merge(project));
	}

	public void remove(Project project) {
		em.remove(em.contains(project) ? project : em.merge(project));
	}
	
}
