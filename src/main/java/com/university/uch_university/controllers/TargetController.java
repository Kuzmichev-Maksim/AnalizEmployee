package com.university.uch_university.controllers;

import com.university.uch_university.model.TargetModel;
import com.university.uch_university.service.DepartmentService;
import com.university.uch_university.service.ProjectService;
import com.university.uch_university.service.TargetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/targets")
public class TargetController {

    @Autowired
    private TargetService targetService;
    @Autowired
    private ProjectService projectService;

    @GetMapping("/all")
    public String getAllTargets(Model model) {
        model.addAttribute("targets", targetService.findAll());
        model.addAttribute("target", new TargetModel());
        model.addAttribute("projects", projectService.findAll());
        return "targetList";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("target") TargetModel target, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("targets", targetService.findAll());
            model.addAttribute("projects", projectService.findAll());
            return "targetList";
        }
        targetService.add(target);
        return "redirect:/targets/all";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("target") TargetModel target, BindingResult result) {
        targetService.add(target);
        return "redirect:/targets/all";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam UUID id) {
        targetService.deleteById(id);
        return "redirect:/targets/all";
    }
}
