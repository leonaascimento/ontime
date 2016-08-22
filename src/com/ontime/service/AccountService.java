package com.ontime.service;

import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ontime.dao.UserDAO;
import com.ontime.model.User;
import com.ontime.util.LoginResult;
import com.ontime.util.RegisterResult;
import com.ontime.util.UserLocator;

@Service
public class AccountService {
	
	private UserDAO dao;
	private UserLocator userLocator;
	private HttpSession session;
	
	@Autowired
	public AccountService(UserDAO dao, HttpSession session, UserLocator userLocator) {
		this.dao = dao;
		this.session = session;
		this.userLocator = userLocator;
	}
	
	public LoginResult login(String username, String password) {
		if (!dao.exists(username))
			return LoginResult.NOT_FOUND;
		
		User user = dao.get(username);
				
		if (!BCrypt.checkpw(password, user.getPassword()))
			return LoginResult.INVALID;
		
		userLocator.setCurrentUser(user);
		
		return LoginResult.SUCCESS;
	}
	
	@Transactional
	public RegisterResult register(User user) {
		if (dao.exists(user.getEmail()))
			return RegisterResult.ALREADY_EXISTS;
		
		String hashpw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(hashpw);
		user.setImageUrl("https://api.adorable.io/avatars/120/" + user.getEmail() + ".png");
		
		dao.add(user);
		return RegisterResult.SUCCESS;
	}
	
	public void logout() {
		session.invalidate();
	}
	
}
