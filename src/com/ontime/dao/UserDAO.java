package com.ontime.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.ontime.model.User;

@Repository
public class UserDAO {
	
	@PersistenceContext
	private EntityManager em;

	public List<User> getList() {
		String criteria = "select u from User u";
		return em.createQuery(criteria, User.class).getResultList();
	}

	public User get(int id) {
		String criteria = "select u from User u where u.id = :id";
		return em.createQuery(criteria, User.class).setParameter("id", id).getSingleResult();
	}

	public User get(String email) {
		String criteria = "select u from User u where u.email = :email";
		return em.createQuery(criteria, User.class).setParameter("email", email).getSingleResult();
	}
	
	public Boolean exists(String email) {
		String criteria = "select u from User u where u.email = :email";
		return em.createQuery(criteria).setParameter("email", email).getResultList().size() > 0;
	}

	public void add(User user) {
		em.persist(user);
	}

	public void update(User user) {
		em.persist(em.contains(user) ? user : em.merge(user));
	}

	public void remove(User user) {
		em.remove(em.contains(user) ? user : em.merge(user));
	}
	
}
