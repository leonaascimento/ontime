package com.ontime.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ontime.service.UserService;
import com.ontime.viewmodel.UserSummaryView;

@Controller
public class UserController {
	
	private UserService service;
	
	@Autowired
	public UserController(UserService service) {
		this.service = service;
	}
	
	@ResponseBody
	@RequestMapping(value = "users", method = RequestMethod.GET)
	public ResponseEntity<?> list() {
		List<UserSummaryView> users = service.getSummaryList();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

}