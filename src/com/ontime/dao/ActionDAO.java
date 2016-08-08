package com.ontime.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.ontime.model.Action;

@Repository
public class ActionDAO {
	
	@PersistenceContext
	private EntityManager context;

	public List<Action> getList() {
		String criteria = "select a from Action c";
		return context.createQuery(criteria, Action.class).getResultList();
	}

	public Action get(int id) {
		String criteria = "select a from Action a where a.id = :id";
		return context.createQuery(criteria, Action.class).setParameter("id", id).getSingleResult();
	}

	public void add(Action action) {
		context.persist(action);
	}

	public void update(Action action) {
		context.persist(action);
	}

	public void remove(Action action) {
		context.remove(action);
	}
	
}
