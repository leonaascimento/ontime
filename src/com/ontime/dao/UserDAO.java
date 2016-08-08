package com.ontime.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.ontime.model.User;

@Repository
public class UserDAO {
	
	@PersistenceContext
	private EntityManager context;

	public List<User> getList() {
		String criteria = "select u from User u";
		return context.createQuery(criteria, User.class).getResultList();
	}

	public User get(int id) {
		String criteria = "select u from User u where u.id = :id";
		return context.createQuery(criteria, User.class).setParameter("id", id).getSingleResult();
	}

	public User get(String email) {
		String criteria = "select u from User u where u.email = :email";
		return context.createQuery(criteria, User.class).setParameter("email", email).getSingleResult();
	}

	public void add(User user) {
		context.persist(user);
	}

	public void update(User user) {
		context.persist(user);
	}

	public void remove(User user) {
		context.remove(user);
	}
	
}
