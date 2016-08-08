package com.ontime.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.ontime.model.Comment;

@Repository
public class CommentDAO {
	
	@PersistenceContext
	private EntityManager context;

	public List<Comment> getList() {
		String criteria = "select c from Comment c";
		return context.createQuery(criteria, Comment.class).getResultList();
	}

	public Comment get(int id) {
		String criteria = "select c from Comment c where c.id = :id";
		return context.createQuery(criteria, Comment.class).setParameter("id", id).getSingleResult();
	}

	public void add(Comment comment) {
		context.persist(comment);
	}

	public void update(Comment comment) {
		context.persist(comment);
	}

	public void remove(Comment comment) {
		context.remove(comment);
	}
	
}
