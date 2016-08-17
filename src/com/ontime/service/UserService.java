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
			UserSummaryViewModel summary = new UserSummaryViewModel(user.getId(), user.getName(), user.getEmail());
			summarized.add(summary);
		}
		
		return summarized;
	}
	
	public UserSummaryViewModel getMyself() {
		User currentUser = userLocator.getCurrentUser();
		
		UserSummaryViewModel myself = new UserSummaryViewModel(currentUser.getId(), currentUser.getName(), currentUser.getEmail());
		
		return myself;
	}
	
}
