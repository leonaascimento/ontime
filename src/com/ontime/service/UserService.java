package com.ontime.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ontime.dao.UserDAO;
import com.ontime.model.User;
import com.ontime.util.UserLocator;
import com.ontime.viewmodel.UserSummaryViewModel;

@Service
public class UserService {
	
	private UserDAO dao;
	private UserLocator userLocator;
	
	@Autowired
	public UserService(UserDAO dao, UserLocator userLocator) {
		this.dao = dao;
		this.userLocator = userLocator;
	}
	
	public List<UserSummaryViewModel> getSummaryList() {
		List<User> users = dao.getList();
		
		List<UserSummaryViewModel> summarized = new ArrayList<>();
		for (User user : users) {
			summarized.add(this.toSummary(user));
		}
		
		return summarized;
	}
	
	public UserSummaryViewModel getSummary(int id) {
		User user = dao.get(id);
		return this.toSummary(user);
	}
	
	public UserSummaryViewModel getMyself() {
		User currentUser = userLocator.getCurrentUser();
		return this.toSummary(currentUser);
	}
	
	private UserSummaryViewModel toSummary(User user) {
		return new UserSummaryViewModel(user);
	}
	
}
