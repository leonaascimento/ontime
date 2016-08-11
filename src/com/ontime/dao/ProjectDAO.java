package com.ontime.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.ontime.model.Project;

@Repository
public class ProjectDAO {
	
	@PersistenceContext
	private EntityManager em;

	public List<Project> getList() {
		String criteria = "select p from Project p";
		return em.createQuery(criteria, Project.class).getResultList();
	}

	public Project get(int id) {
		String criteria = "select p from Project p left join fetch p.tasks where p.id = :id";
		return em.createQuery(criteria, Project.class).setParameter("id", id).getSingleResult();
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
