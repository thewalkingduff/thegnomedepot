package com.devduffy.gnomedepot.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.devduffy.gnomedepot.entity.User;
import com.devduffy.gnomedepot.service.UserService;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class UserController {
    // List<User> users = new ArrayList<>();

    @Autowired
    UserService userService;

    @GetMapping("/user/login")
    public String getLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/user/loginUser")
    public String loginUser(@Valid User user, BindingResult result) {
        if(user.getLastName().equals(user.getFirstName())) result.rejectValue("lastName", "", "Last name can not equal first name.");
        if(result.hasErrors()) return "login";
        // userService.saveUser(user);
        return "redirect:/result"; 
    }

    @GetMapping("/user/register")
    public String getRegisterForm(Model model) {
        model.addAttribute("user", new User());
        log.debug("This is in the GET method for create user.");
        return "register";
    }

    @PostMapping("/user/register")
    public String createUser(@Valid User user, BindingResult result) {
        if(user.getLastName().equals(user.getFirstName())) result.rejectValue("lastName", "", "Last name can not equal first name.");
        if(result.hasErrors()) return "register";
        user.setCreateDate(new Date());
        userService.saveUser(user);
        log.debug(user.toString());
        return "redirect:/result"; 
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable Integer id) {
        return userService.getUser(id);
    }

    @GetMapping("/{users}")
    public List<User> getAllUsers() {
        log.info("simple log");
        return userService.getUsers();
    }

    @GetMapping("/result")
    public String getResult() {
        return "result";
    }

}
