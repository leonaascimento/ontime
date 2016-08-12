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
	public String list(Model model) {
		List<Project> projects = service.getList();
		model.addAttribute("projects", projects);
		return "project/list";
	}
	
	@RequestMapping(value = "projects/details", method = RequestMethod.GET)
	public String details(@RequestParam final int id, Model model) {
		Project project = service.get(id);
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
	public String create(@Valid Project project, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "project/create";
		}
		
		service.add(project);
		return "redirect:/projects";
	}
	
	@RequestMapping(value = "projects/edit", method = RequestMethod.GET)
	public String edit(@RequestParam final int id, Model model) {
		Project project = service.get(id);
		model.addAttribute(project);
		return "project/edit";
	}
	
	@RequestMapping(value = "projects/edit", method = RequestMethod.POST)
	public String edit(@Valid Project project, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "project/edit";
		}
		
		service.update(project);
		return "redirect:/projects";
	}
	
	@RequestMapping(value = "projects/remove", method = RequestMethod.GET)
	public String remove(@RequestParam final int id, Model model) {
		Project project = service.get(id);
		model.addAttribute(project);
		return "project/remove";
	}
	
	@RequestMapping(value = "projects/remove", method = RequestMethod.POST)
	public String remove(@RequestParam final int id) {
		service.remove(id);
		return "redirect:/projects";
	}
	
}
