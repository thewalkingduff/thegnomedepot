package com.devduffy.gnomedepot.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.devduffy.gnomedepot.entity.Order;
import com.devduffy.gnomedepot.entity.Product;
import com.devduffy.gnomedepot.entity.User;
import com.devduffy.gnomedepot.security.AuthenticatedUserService;
import com.devduffy.gnomedepot.security.UserDetailsServiceImpl;
import com.devduffy.gnomedepot.service.OrderService;
import com.devduffy.gnomedepot.service.ProductService;
import com.devduffy.gnomedepot.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class OrderController {

    OrderService orderService;
    ProductService productService;
    UserService userService;
    AuthenticatedUserService authenticatedUserService;

    @Autowired
    public OrderController(OrderService orderService, ProductService productService, UserService userService, AuthenticatedUserService authenticatedUserService) {
        this.orderService = orderService;
        this.productService = productService;
        this.userService = userService;
        this.authenticatedUserService = authenticatedUserService;
    }

   
 
}
