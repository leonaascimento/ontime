package com.ontime.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ontime.dao.UserDAO;
import com.ontime.model.User;
import com.ontime.viewmodel.UserSummaryView;

@Service
public class UserService {
	
	private UserDAO dao;
	
	@Autowired
	public UserService(UserDAO dao) {
		this.dao = dao;
	}
	
	public List<UserSummaryView> getSummaryList() {
		List<User> users = dao.getList();
		
		List<UserSummaryView> summarized = new ArrayList<>();
		for (User user : users) {
			UserSummaryView summary = new UserSummaryView(user.getId(), user.getName(), user.getEmail());
			summarized.add(summary);
		}
		
		return summarized;
	}
	
}
