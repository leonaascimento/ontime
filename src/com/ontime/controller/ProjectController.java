package com.ontime.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ontime.dao.ProjectDAO;
import com.ontime.model.Project;

@Controller
public class ProjectController {

	private ProjectDAO dao;

	@Autowired
	public ProjectController(ProjectDAO dao) {
		this.dao = dao;
	}

	@RequestMapping(value = "projects/details", method = RequestMethod.GET)
	public String index(@RequestParam int id, Model model) {
		Project project = dao.get(id);
		System.out.println("Req. " + project.getName());
		model.addAttribute("name", project.getName());
		return "project/details";
	}

}
