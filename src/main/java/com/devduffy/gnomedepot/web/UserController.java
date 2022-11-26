package com.devduffy.gnomedepot.web;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.devduffy.gnomedepot.Constants;
import com.devduffy.gnomedepot.entity.User;
import com.devduffy.gnomedepot.repository.UserRepository;
import com.devduffy.gnomedepot.service.UserService;

@Controller
// @RequestMapping("/user")
public class UserController {
    // List<User> users = new ArrayList<>();

    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id) {
        return userService.getUser(id);
    }

    @GetMapping("/{users}")
    public List<User> getAllUsers() {
        return userService.getUsers();
    }

    @GetMapping("/register")
    public String getRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/submitUser")
    public String handleSubmit(@Valid User user, BindingResult result) {
        if(user.getLastName().equals(user.getFirstName())) result.rejectValue("lastName", "", "Last name can not equal first name.");
        if(result.hasErrors()) return "register";
        userService.saveUser(user);
        return "redirect:/result"; 
    }

    @GetMapping("/result")
    public String getResult() {
        return "result";
    }

    // public int getUserIndex(String id) {
    //     for (int i = 0; i < users.size(); i++) {
    //         if (users.get(i).getId().equals(id)) return i;
    //     }
    //     return Constants.NOT_FOUND;
    // }


}
