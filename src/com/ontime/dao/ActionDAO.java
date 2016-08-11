package com.ontime.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.ontime.model.Action;

@Repository
public class ActionDAO {
	
	@PersistenceContext
	private EntityManager em;

	public List<Action> getList() {
		String criteria = "select a from Action c";
		return em.createQuery(criteria, Action.class).getResultList();
	}

	public Action get(int id) {
		String criteria = "select a from Action a where a.id = :id";
		return em.createQuery(criteria, Action.class).setParameter("id", id).getSingleResult();
	}

	public void add(Action action) {
		em.persist(action);
	}

	public void update(Action action) {
		em.persist(em.contains(action) ? action : em.merge(action));
	}

	public void remove(Action action) {
		em.remove(em.contains(action) ? action : em.merge(action));
	}
	
}
