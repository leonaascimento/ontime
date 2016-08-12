package com.ontime.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.ontime.model.Task;

@Repository
public class TaskDAO {
	
	@PersistenceContext
	private EntityManager em;

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
