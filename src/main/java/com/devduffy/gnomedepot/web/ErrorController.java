package com.devduffy.gnomedepot.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@ControllerAdvice
public class ErrorController {
    
    @RequestMapping(value = "/error/404")
    public String error404(HttpServletRequest request) {
        String originalUri = request.getRequestURI();
        

        return "error/404";
    }
}
