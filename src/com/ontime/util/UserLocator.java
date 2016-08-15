package com.ontime.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ontime.model.User;

@Component
@Scope(value="request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserLocator {
	
	public final String USER_SESSION_KEY = "SESSION_USER_KEY";
	
	private User currentUser;
	private HttpSession session;
	
	@Autowired
	public UserLocator(HttpServletRequest req) {
		this.session = req.getSession();
	}
	
	public User getCurrentUser() {
		/*
		if (user == null) {
			user = (User) session.getAttribute(USER_SESSION_KEY);
		}
		*/
		
		currentUser = new User();
		currentUser.setId(1);
		currentUser.setEmail("leonardo@ontime.com");
		
		return currentUser;
	}
	
	public void setCurrentUser(User user) {
		session.setAttribute(USER_SESSION_KEY, user);
	}

}
