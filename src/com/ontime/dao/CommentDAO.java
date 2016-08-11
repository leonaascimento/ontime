package com.ontime.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.ontime.model.Comment;

@Repository
public class CommentDAO {
	
	@PersistenceContext
	private EntityManager em;

	public List<Comment> getList() {
		String criteria = "select c from Comment c";
		return em.createQuery(criteria, Comment.class).getResultList();
	}

	public Comment get(int id) {
		String criteria = "select c from Comment c where c.id = :id";
		return em.createQuery(criteria, Comment.class).setParameter("id", id).getSingleResult();
	}

	public void add(Comment comment) {
		em.persist(comment);
	}

	public void update(Comment comment) {
		em.persist(em.contains(comment) ? comment : em.merge(comment));
	}

	public void remove(Comment comment) {
		em.remove(em.contains(comment) ? comment : em.merge(comment));
	}
	
}
