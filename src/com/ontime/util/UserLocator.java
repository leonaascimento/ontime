package com.ontime.util;

import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ontime.model.User;

@Component
@Scope(value="request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserLocator {
	
	public final String USER_SESSION_KEY = "SESSION_KEY_USER";
	
	private HttpSession session;
	
	@Autowired
	public UserLocator(HttpSession session) {
		this.session = session;
	}
	
	public User getCurrentUser() {
		return (User) session.getAttribute(USER_SESSION_KEY);
	}
	
	public void setCurrentUser(User currentUser) {
		session.setAttribute(USER_SESSION_KEY, currentUser);
	}

}
