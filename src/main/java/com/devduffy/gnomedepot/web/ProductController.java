package com.devduffy.gnomedepot.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ProductController {
    
    
    @GetMapping("/")
    public String homePage(Model model, @RequestParam(required = false) String productName, @RequestParam(required = false) String productCategory ) {
        return "home";
    }
}
