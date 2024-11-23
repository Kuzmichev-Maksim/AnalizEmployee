package com.university.uch_university.controllers;

import com.university.uch_university.model.EmployeeModel;
import com.university.uch_university.model.PostModel;
import com.university.uch_university.service.EmployeeService;
import com.university.uch_university.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/all")
    public String listPosts(Model model) {
        List<PostModel> posts = postService.findAll();
        model.addAttribute("posts", posts);

        List<EmployeeModel> employees = employeeService.findAll();
        model.addAttribute("employees", employees);
        model.addAttribute("allEmployees", employees);

        model.addAttribute("post", new PostModel());

        return "postList";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("post") PostModel post, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("posts", postService.findAll());
            model.addAttribute("employees", employeeService.findAll());
            return "postList";
        }
        postService.add(post);
        return "redirect:/posts/all";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("post") PostModel post, BindingResult result, Model model) {
        postService.add(post);
        return "redirect:/posts/all";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam UUID id) {
        postService.delete(id);
        return "redirect:/posts/all";
    }

}
