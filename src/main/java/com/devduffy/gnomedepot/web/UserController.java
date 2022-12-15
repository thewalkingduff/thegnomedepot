package com.devduffy.gnomedepot.web;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.devduffy.gnomedepot.entity.User;
import com.devduffy.gnomedepot.entity.UserRole;
import com.devduffy.gnomedepot.form.CreateUserForm;
import com.devduffy.gnomedepot.repository.UserRoleRepository;
import com.devduffy.gnomedepot.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
	@Qualifier("passwordEncoder")
	private PasswordEncoder passwordEncoder;

    @GetMapping("/user/login")

    public String getLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/user/login")
    public String loginUser(@Valid User user, BindingResult result) {
        if (user.getLastName().equals(user.getFirstName()))
            result.rejectValue("lastName", "", "Last name can not equal first name.");
        if (result.hasErrors())
            return "login";
        return "redirect:/result";
    }

    @GetMapping("/user/register")
    public String getRegisterForm(Model model) {
        model.addAttribute("createUserForm", new CreateUserForm());
        log.debug("This is in the GET method for create user.");
        return "register";
    }

    @PostMapping("/user/register")
    public String createUser(@Valid CreateUserForm form, BindingResult result) {
        if (form.getLastName().equals(form.getFirstName()))
            result.rejectValue("lastName", "", "Last name can not equal first name.");
        if (!(form.getPassword().equals(form.getConfirmPassword())))
            result.rejectValue("confirmPassword", "", "Passwords do not match. Please try again.");

        if (result.hasErrors())
            return "register";

        User user = new User();
        String encodedPassword = passwordEncoder.encode(form.getPassword());
        user.setPassword(encodedPassword);
        user.setCreateDate(new Date());
        user.setFirstName(form.getFirstName());
        user.setLastName(form.getLastName());
        user.setUsername(form.getUsername());
        user.setEmail(form.getEmail());
        user.setAddress1(form.getAddress());
        user.setCity(form.getCity());
        user.setState(form.getState());
        user.setPostalCode(form.getZip());
        user.setPhone(form.getPhone());
        userService.saveUser(user);

        UserRole ur = new UserRole();
        ur.setRoleName("USER");
        ur.setUserId(user.getId());
        userRoleRepository.save(ur);
        return "success-register";
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

    @GetMapping("/user/admin")
    public String getAdmin() {
        return "admin/admin";
    }

}
