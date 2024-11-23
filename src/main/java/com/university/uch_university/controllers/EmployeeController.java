package com.university.uch_university.controllers;

import com.university.uch_university.model.EmployeeModel;
import com.university.uch_university.service.EmployeeService;
import com.university.uch_university.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private PostService postService;

    @GetMapping("/all")
    public String getAllPosts(Model model) {
        model.addAttribute("employees", employeeService.findAll());
        model.addAttribute("employee", new EmployeeModel());
        model.addAttribute("posts", postService.findAll());
        return "employeeList";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("employee") EmployeeModel employee, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("employees", employeeService.findAll());
            model.addAttribute("posts", postService.findAll());
            return "employeeList";
        }
        employeeService.add(employee);
        return "redirect:/employees/all";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("employee") EmployeeModel employeeModel, BindingResult result) {
        employeeService.add(employeeModel);
        return "redirect:/employees/all";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam UUID id) {
        employeeService.deleteById(id);
        return "redirect:/employees/all";
    }
}
