package com.ontime.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ontime.model.Comment;
import com.ontime.model.Task;
import com.ontime.service.CommentService;
import com.ontime.service.TaskService;

@Controller
public class CommentController {

	private CommentService commentService;
	private TaskService taskService;

	@Autowired
	public CommentController(CommentService commentService, TaskService taskService) {
		this.commentService = commentService;
		this.taskService = taskService;
	}

	@RequestMapping(value = "projects/{projectId}/tasks/{taskId}/comments", method = RequestMethod.GET)
	public String list(@PathVariable final int projectId, @PathVariable final int taskId, Model model) {
		Task task = taskService.get(taskId);
		List<Comment> comments = commentService.getList(taskId);
		model.addAttribute(task);
		model.addAttribute("comments", comments);
		return "comment/list";
	}

	@ResponseBody
	@RequestMapping(value = "/api/projects/{projectId}/tasks/{taskId}/comments/create", method = RequestMethod.POST)
	public ResponseEntity<?> apiCreate(@PathVariable final int projectId, @PathVariable final int taskId,
			@Valid Comment comment, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
		}

		commentService.add(comment, taskId);

		return new ResponseEntity<>(HttpStatus.CREATED);
	}

}
