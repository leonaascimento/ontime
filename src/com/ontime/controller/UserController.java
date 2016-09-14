package com.ontime.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ontime.service.UserService;
import com.ontime.viewmodel.UserSummaryViewModel;

@Controller
public class UserController {
	
	private UserService service;
	
	@Autowired
	public UserController(UserService service) {
		this.service = service;
	}
	
	@RequestMapping(value = "users", method = RequestMethod.GET)
	public String list(Model model) {
		List<UserSummaryViewModel> users = service.getSummaryList();
		model.addAttribute("users", users);
		return "user/list";
	}
	
	@RequestMapping(value = "users/{userId}", method = RequestMethod.GET)
	public String details(@PathVariable final int userId, Model model) {
		UserSummaryViewModel user = service.getSummary(userId);
		model.addAttribute("user", user);
		return "user/details";
	}
	
	@ResponseBody
	@RequestMapping(value = "api/users", method = RequestMethod.GET)
	public ResponseEntity<?> apiList() {
		List<UserSummaryViewModel> users = service.getSummaryList();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value = "api/users/myself", method = RequestMethod.GET)
	public ResponseEntity<?> apiMyself() {
		UserSummaryViewModel user = service.getMyself();
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

}
