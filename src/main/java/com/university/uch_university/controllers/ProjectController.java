package com.university.uch_university.controllers;

import com.university.uch_university.model.ProjectModel;
import com.university.uch_university.service.DepartmentService;
import com.university.uch_university.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/all")
    public String getAllProjects(Model model) {
        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("project", new ProjectModel());
        model.addAttribute("departments", departmentService.findAll());
        return "projectList";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("project") ProjectModel project, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("projects", projectService.findAll());
            model.addAttribute("departments", departmentService.findAll());
            return "projectList";
        }
        projectService.add(project);
        return "redirect:/projects/all";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("project") ProjectModel projectModel, BindingResult result) {
        projectService.add(projectModel);
        return "redirect:/projects/all";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam UUID id) {
        projectService.deleteById(id);
        return "redirect:/projects/all";
    }
}
