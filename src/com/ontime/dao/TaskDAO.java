package com.ontime.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.ontime.model.Task;
import com.ontime.model.TaskStatus;

@Repository
public class TaskDAO {
	
	@PersistenceContext
	private EntityManager em;

	public List<Task> getList(int projectId) {
		String criteria = "select t from Task t where t.project.id = :projectId order by t.createdAt desc";
		return em.createQuery(criteria, Task.class).setParameter("projectId", projectId).getResultList();
	}
	
	public List<Task> getList(int projectId, TaskStatus status) {
		String criteria = "select t from Task t where t.project.id = :projectId and t.status = :status";
		return em.createQuery(criteria, Task.class).setParameter("projectId", projectId).setParameter("status", status).getResultList();
	}
	
	public Task get(int id) {
		String criteria = "select t from Task t where t.id = :id";
		return em.createQuery(criteria, Task.class).setParameter("id", id).getSingleResult();
	}

	public void add(Task task) {
		em.persist(task);
	}

	public void update(Task task) {
		em.persist(em.contains(task) ? task : em.merge(task));
	}

	public void remove(Task task) {
		em.remove(em.contains(task) ? task : em.merge(task));
	}
	
}
