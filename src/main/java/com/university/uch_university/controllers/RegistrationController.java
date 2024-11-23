package com.university.uch_university.controllers;

import com.university.uch_university.model.RoleEnum;
import com.university.uch_university.model.UserModel;
import com.university.uch_university.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/registration")
    public String registrationView(Model model) {
        model.addAttribute("user", new UserModel());
        return "regis";
    }

    @PostMapping("/registration")
    public String registerUser(@Valid UserModel user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("message", "Пожалуйста, исправьте ошибки в форме.");
            return "registration";
        }
        if (userRepository.existsByUsername(user.getUsername())) {
            model.addAttribute("message", "Пользователь уже существует.");
            return "registration";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singleton(RoleEnum.MANAGER));

        userRepository.save(user);
        return "redirect:/login";
    }
}
