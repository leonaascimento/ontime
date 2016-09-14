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
		String criteria = "select p from Project p order by p.createdAt desc";
		return em.createQuery(criteria, Project.class).getResultList();
	}
	
	public List<Project> getList(int createdById) {
		String criteria = "select p from Project p where p.createdBy.id = :createdById";
		return em.createQuery(criteria, Project.class).setParameter("createdById", createdById).getResultList();
	}

	public Project get(int id) {
		String criteria = "select p from Project p where p.id = :id";
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
