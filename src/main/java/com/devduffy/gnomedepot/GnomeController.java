package com.devduffy.gnomedepot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GnomeController {
    
    @GetMapping("/")
    public String homePage() {
        return "home";
    }
}
