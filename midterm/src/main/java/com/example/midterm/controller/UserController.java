package com.example.midterm.controller;

import com.example.midterm.model.User;
import com.example.midterm.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @Autowired
    private UserServiceImpl userService;


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user) {
        userService.login(user);
        return "redirect:/products";
    }

    @GetMapping("/register")
    public String register(final Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String userRegistration(@ModelAttribute("user") User user) {
        userService.register(user);
        return "redirect:/login";
    }


}
