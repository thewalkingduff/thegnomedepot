package com.devduffy.gnomedepot.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.devduffy.gnomedepot.entity.User;
import com.devduffy.gnomedepot.repository.UserRepository;
import com.devduffy.gnomedepot.service.UserService;

@Controller
public class UserController {
    
    @Autowired
    UserService userService;

    @GetMapping("/register")
    public String getRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/submitUser")
    public String handleSubmit(User user) {
        userService.saveUser(user);
        return "result";
    }
}
