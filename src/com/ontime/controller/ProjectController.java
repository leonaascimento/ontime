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
import org.springframework.web.bind.annotation.RequestParam;

import com.ontime.model.Project;
import com.ontime.service.ProjectService;

@Controller
public class ProjectController {

	private ProjectService service;

	@Autowired
	public ProjectController(ProjectService service) {
		this.service = service;
	}
	
	@RequestMapping(value = "projects", method = RequestMethod.GET)
	public String list(@RequestParam(required = false) final Integer userId, Model model) {
		List<Project> projects;
		
		if (userId != null) {
			projects = service.getList(userId);
		} else {
			projects = service.getList();
		}
		
		model.addAttribute("projects", projects);
		return "project/list";
	}
	
	@RequestMapping(value = "projects/{projectId}", method = RequestMethod.GET)
	public String details(@PathVariable final int projectId, Model model) {
		Project project = service.get(projectId);
		model.addAttribute(project);
		return "project/details";
	}
	
	@RequestMapping(value = "projects/create", method = RequestMethod.GET)
	public String create(Model model) {
		Project project = new Project();
		model.addAttribute(project);
		return "project/create";
	}
	
	@RequestMapping(value = "projects/create", method = RequestMethod.POST)
	public String create(@Valid Project project, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "project/create";
		}
		
		service.add(project);
		return "redirect:/projects";
	}
	
	@RequestMapping(value = "projects/{projectId}/edit", method = RequestMethod.GET)
	public String edit(@PathVariable final int projectId, Model model) {
		Project project = service.get(projectId);
		model.addAttribute(project);
		return "project/edit";
	}
	
	@RequestMapping(value = "projects/{projectId}/edit", method = RequestMethod.POST)
	public String edit(@PathVariable final int projectId, @Valid Project project, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "project/edit";
		}
		
		service.update(project);
		return "redirect:/projects";
	}
	
	@RequestMapping(value = "projects/{projectId}/remove", method = RequestMethod.GET)
	public String remove(@PathVariable final int projectId, Model model) {
		Project project = service.get(projectId);
		model.addAttribute(project);
		return "project/remove";
	}
	
	@RequestMapping(value = "projects/{projectId}/remove", method = RequestMethod.POST)
	public String remove(@PathVariable final int projectId) {
		service.remove(projectId);
		return "redirect:/projects";
	}
	
}
