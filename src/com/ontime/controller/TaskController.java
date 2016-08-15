package com.ontime.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ontime.model.Task;
import com.ontime.service.TaskService;

@Controller
public class TaskController {

	private TaskService service;

	@Autowired
	public TaskController(TaskService service) {
		this.service = service;
	}

	@RequestMapping(value = "projects/{projectId}/tasks", method = RequestMethod.GET)
	public String list(@PathVariable final int projectId, Model model) {
		List<Task> tasks = service.getList(projectId);
		model.addAttribute("projectId", projectId);
		model.addAttribute("tasks", tasks);
		return "task/list";
	}

	@RequestMapping(value = "projects/{projectId}/tasks/{taskId}", method = RequestMethod.GET)
	public String details(@PathVariable final int projectId, @PathVariable final int taskId, Model model) {
		Task task = service.get(taskId);
		model.addAttribute("projectId", projectId);
		model.addAttribute(task);
		return "task/details";
	}

	@RequestMapping(value = "projects/{projectId}/tasks/create", method = RequestMethod.GET)
	public String create(@PathVariable final int projectId, Model model) {
		Task task = new Task();
		model.addAttribute("projectId", projectId);
		model.addAttribute(task);
		return "task/create";
	}

	@RequestMapping(value = "projects/{projectId}/tasks/create", method = RequestMethod.POST)
	public String create(@PathVariable final int projectId, @Valid Task task, BindingResult result, Model model,
			RedirectAttributes redirect) {
		if (result.hasErrors()) {
			model.addAttribute("projectId", projectId);
			return "task/create";
		}
		service.add(task, projectId);
		redirect.addAttribute("projectId", projectId);
		return "redirect:/projects/{projectId}/tasks";
	}

	@RequestMapping(value = "projects/{projectId}/tasks/{taskId}/edit", method = RequestMethod.GET)
	public String edit(@PathVariable final int projectId, @PathVariable final int taskId, Model model) {
		Task task = service.get(taskId);
		model.addAttribute("projectId", projectId);
		model.addAttribute(task);
		return "task/edit";
	}

	@RequestMapping(value = "projects/{projectId}/tasks/{taskId}/edit", method = RequestMethod.POST)
	public String edit(@PathVariable final int projectId, @PathVariable final int taskId, @Valid Task task,
			BindingResult result, Model model, RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return "task/edit";
		}
		service.update(task);
		redirect.addAttribute("projectId", projectId);
		return "redirect:/projects/{projectId}/tasks";
	}

	@RequestMapping(value = "projects/{projectId}/tasks/{taskId}/remove", method = RequestMethod.GET)
	public String remove(@PathVariable final int projectId, @PathVariable final int taskId, Model model) {
		Task task = service.get(taskId);
		model.addAttribute("projectId", projectId);
		model.addAttribute(task);
		return "task/remove";
	}

	@RequestMapping(value = "projects/{projectId}/tasks/{taskId}/remove", method = RequestMethod.POST)
	public String remove(@PathVariable final int projectId, @PathVariable final int taskId,
			RedirectAttributes redirect) {
		service.remove(taskId);
		redirect.addAttribute("projectId", projectId);
		return "redirect:/projects/{projectId}/tasks";
	}

}
