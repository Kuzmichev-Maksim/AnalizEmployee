package com.university.uch_university.controllers;

import com.university.uch_university.model.DepartmentModel;
import com.university.uch_university.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/departments")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/all")
    public String getAllPosts(Model model) {
        model.addAttribute("departments", departmentService.findAll());
        model.addAttribute("department", new DepartmentModel());
        return "departmentList";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("department") DepartmentModel department, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("departments", departmentService.findAll());
            return "departmentList";
        }
        departmentService.add(department);
        return "redirect:/departments/all";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("department") DepartmentModel departmentModel, BindingResult result) {
        departmentService.add(departmentModel);
        return "redirect:/departments/all";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam UUID id) {
        departmentService.deleteById(id);
        return "redirect:/departments/all";
    }
}
