package com.ontime.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ontime.model.Comment;
import com.ontime.service.CommentService;
import com.pusher.rest.Pusher;

@Controller
public class CommentController {
	
	private CommentService service;
	private Pusher pusher;
	private final String appId = "237083";
	private final String apiKey = "1903bfdf2d61093ea14d";
	private final String apiSecret = "f7a3becb1b5393e391df";

	@Autowired
	public CommentController(CommentService service) {
		this.service = service;
		pusher = new Pusher(appId, apiKey, apiSecret);
	}
	
	@RequestMapping(value = "comments", method = RequestMethod.GET)
	public String list(@RequestParam final int taskId, Model model) {
		List<Comment> comments = service.getList(taskId);
		model.addAttribute("comments", comments);
		return "comment/list";
	}
	
	@RequestMapping(value = "comments/create", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> create(@Valid Comment comment, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
		}

		service.add(comment);
		
		pusher.trigger("channel-one", "new_comment", comment);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
}