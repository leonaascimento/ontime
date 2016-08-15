package com.ontime.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ontime.model.Task;
import com.ontime.service.TaskService;

@Controller
public class TaskController {
	
	private TaskService service;

	@Autowired
	public TaskController(TaskService service) {
		this.service = service;
	}
	
	@RequestMapping(value = "tasks", method = RequestMethod.GET)
	public String list(@RequestParam final int projectId, Model model) {
		List<Task> tasks = service.getList(projectId);
		model.addAttribute("tasks", tasks);
		return "task/list";
	}
	
	@RequestMapping(value = "tasks/details", method = RequestMethod.GET)
	public String details(@RequestParam final int id, Model model) {
		Task task = service.get(id);
		model.addAttribute(task);
		return "task/details";
	}
	
	@RequestMapping(value = "tasks/create", method = RequestMethod.GET)
	public String create(@RequestParam final int projectId, Model model) {
		Task task = new Task();
		model.addAttribute(task);
		return "task/create";
	}
	
	@RequestMapping(value = "tasks/create", method = RequestMethod.POST)
	public String create(@RequestParam final int projectId, @Valid Task task, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "task/create";
		}
		
		service.add(task);
		return "redirect:/tasks";
	}
	
	@RequestMapping(value = "tasks/edit", method = RequestMethod.GET)
	public String edit(@RequestParam final int id, Model model) {
		Task task = service.get(id);
		model.addAttribute(task);
		return "task/edit";
	}
	
	@RequestMapping(value = "tasks/edit", method = RequestMethod.POST)
	public String edit(@Valid Task task, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "task/edit";
		}
		
		service.update(task);
		return "redirect:/tasks";
	}
	
	@RequestMapping(value = "tasks/remove", method = RequestMethod.GET)
	public String remove(@RequestParam final int id, Model model) {
		Task task = service.get(id);
		model.addAttribute(task);
		return "task/remove";
	}
	
	@RequestMapping(value = "tasks/remove", method = RequestMethod.POST)
	public String remove(@RequestParam final int id) {
		service.remove(id);
		return "redirect:/tasks";
	}
	
}
