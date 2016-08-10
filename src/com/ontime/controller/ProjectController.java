package com.ontime.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ontime.model.Project;
import com.ontime.service.ProjectService;

@Controller
public class ProjectController {

	private ProjectService projectService;

	@Autowired
	public ProjectController(ProjectService service) {
		this.projectService = service;
	}
	
	@RequestMapping(value = "projects", method = RequestMethod.GET)
	public String list(Model model) {
		List<Project> projects = projectService.getList();
		model.addAttribute("projects", projects);
		return "project/list";
	}
	
	@RequestMapping(value = "projects/details", method = RequestMethod.GET)
	public String details(@RequestParam final int id, Model model) {
		Project project = projectService.get(id);
		model.addAttribute(project);
		return "project/details";
	}
	
	@RequestMapping(value = "projects/create", method = RequestMethod.GET)
	public String create() {
		return "project/create";
	}
	
	@RequestMapping(value = "projects/create", method = RequestMethod.POST)
	public String create(Project project, Model model) {
		projectService.add(project);
		return "redirect:/projects";
	}
	
	@RequestMapping(value = "projects/edit", method = RequestMethod.GET)
	public String edit(@RequestParam final int id, Model model) {
		Project project = projectService.get(id);
		model.addAttribute(project);
		return "project/edit";
	}
	
	@RequestMapping(value = "projects/edit", method = RequestMethod.POST)
	public String edit(Project project, Model model) {
		projectService.update(project);
		return "redirect:/projects";
	}
	
	@RequestMapping(value = "projects/remove", method = RequestMethod.GET)
	public String remove(@RequestParam final int id, Model model) {
		Project project = projectService.get(id);
		model.addAttribute(project);
		return "project/edit";
	}
	
	@RequestMapping(value = "projects/remove", method = RequestMethod.POST)
	public String remove(Project project, Model model) {
		projectService.remove(project);
		return "redirect:/projects";
	}
	
}
