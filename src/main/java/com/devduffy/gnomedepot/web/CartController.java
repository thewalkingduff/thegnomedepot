package com.devduffy.gnomedepot.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.devduffy.gnomedepot.dto.OrderDTO;
import com.devduffy.gnomedepot.dto.OrderDetailsDTO;
import com.devduffy.gnomedepot.dto.ProductDTO;
import com.devduffy.gnomedepot.entity.Order;
import com.devduffy.gnomedepot.entity.Product;
import com.devduffy.gnomedepot.entity.User;
import com.devduffy.gnomedepot.security.AuthenticatedUserService;
import com.devduffy.gnomedepot.security.UserDetailsServiceImpl;
import com.devduffy.gnomedepot.service.OrderDetailsService;
import com.devduffy.gnomedepot.service.OrderService;
import com.devduffy.gnomedepot.service.ProductService;
import com.devduffy.gnomedepot.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class CartController {

    OrderService orderService;
    OrderDetailsService orderDetailsService;
    ProductService productService;
    UserService userService;
    AuthenticatedUserService authenticatedUserService;

    @Autowired
    public CartController(OrderService orderService, ProductService productService, UserService userService, AuthenticatedUserService authenticatedUserService,
    OrderDetailsService orderDetailsService) {
        this.orderService = orderService;
        this.productService = productService;
        this.userService = userService;
        this.authenticatedUserService = authenticatedUserService;
        this.orderDetailsService = orderDetailsService;
    }

    @GetMapping("/cart")
    public String getOrderItems(Model model) {
        User user = authenticatedUserService.getCurrentUser();
        Order cart;
        // Order cart = orderService.getOrderByUser(user);

        // List<OrderDetailsDTO> orderDetailsDTOs = orderDetailsService.getProduct()(order).stream()
        //     .map(e -> e.toDTO()).collect(Collectors.toList());
        
        // List<ProductDTO> productDTOs = productService.getProducts().stream()
        //     .map(e -> e.toDTO()).collect(Collectors.toList());
        
        // Collections.shuffle(productDTOs);

        // Double totalPrice = orderDetailsDTOs.stream()
        //     .map(e -> e.getQuantity() * e.getProductDTO().getPrice())
        //     .reduce(0.0, Double::sum);

        // model.addAttribute("productDTOs", productDTOs);
        // model.addAttribute("orderDetailsDTOs", orderDetailsDTOs);
        if(orderService.getOrderByUser(user) == null) {
            cart = new Order();
            cart.setUser(user);
        } else {
            cart = orderService.getOrderByUser(user);
        }
        
        
        List<Product> productsInCart = cart.getProducts();
        // productsInCart.add(productService.getProduct(5));
        // List<Product> productsInCart = new ArrayList<Product>();
        // productsInCart.add(productService.getProduct(5));
        model.addAttribute("productsInCart", productsInCart);
        return "cart";
    }

    @GetMapping("/cart/add")
    public String addToOrder(@RequestParam("id") Integer id) {
        User user = authenticatedUserService.getCurrentUser();
        Product product = productService.getProduct(id);
        Order cart;
        if(orderService.getOrderByUser(user) == null) {
            cart = new Order();
            cart.setUser(user);
        } else {
            cart = orderService.getOrderByUser(user);
        }
        orderService.addToCart(product, cart, user);
        return "redirect:/cart";
    }

    @DeleteMapping("/cart/delete/{itemId}")
    public String deleteOrderItem(Integer productId) {
        return "home";
    }

    @PutMapping("cart/update/{itemId}")
    public String updateOrderItem(Integer productId, Integer quantity, User userToken) {
        return "home";
    }
}
