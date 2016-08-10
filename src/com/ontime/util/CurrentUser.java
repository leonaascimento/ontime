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
public class CurrentUser {
	
	public final String USER_SESSION_KEY = "SESSION_USER_KEY";
	
	private User user;
	private HttpSession session;
	
	@Autowired
	public CurrentUser(HttpServletRequest req) {
		this.session = req.getSession();
	}
	
	public User getUser() {
		/*
		if (user == null) {
			user = (User) session.getAttribute(USER_SESSION_KEY);
		}
		*/
		
		user = new User();
		user.setId(1);
		user.setEmail("leonardo@ontime.com");
		
		return user;
	}
	
	public void setUser(User user) {
		session.setAttribute(USER_SESSION_KEY, user);
	}

}
