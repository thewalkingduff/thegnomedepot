package com.devduffy.gnomedepot.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.devduffy.gnomedepot.entity.Order;
import com.devduffy.gnomedepot.entity.Product;
import com.devduffy.gnomedepot.entity.User;
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

    @Autowired
    public OrderController(OrderService orderService, ProductService productService, UserService userService) {
        this.orderService = orderService;
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping("/order/cart")
    public String getCart(Model model, User user) {
        model.addAttribute("cart", orderService.getAllOrdersOfUser(user.getId()));
        return "cart";
    }

    @GetMapping("/order/addToCart")
    public String addToOrder(@RequestParam("id") Integer id) {
        Product product = productService.getProduct(id);
        orderService.addToCart(product, new Order(), userService.getUser(1));
        return "redirect:/order/cart";
    }
 
}
