package com.devduffy.gnomedepot.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.devduffy.gnomedepot.dto.OrderDTO;
import com.devduffy.gnomedepot.dto.OrderDetailsDTO;
import com.devduffy.gnomedepot.dto.ProductDTO;
import com.devduffy.gnomedepot.entity.Order;
import com.devduffy.gnomedepot.entity.OrderDetails;
import com.devduffy.gnomedepot.entity.Product;
import com.devduffy.gnomedepot.entity.User;
import com.devduffy.gnomedepot.form.CartItem;
import com.devduffy.gnomedepot.security.AuthenticatedUserService;
import com.devduffy.gnomedepot.security.UserDetailsServiceImpl;
import com.devduffy.gnomedepot.service.OrderDetailsService;
import com.devduffy.gnomedepot.service.OrderService;
import com.devduffy.gnomedepot.service.ProductService;
import com.devduffy.gnomedepot.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Controller
public class CartController {

    OrderService orderService;
    OrderDetailsService orderDetailsService;
    ProductService productService;
    UserService userService;
    AuthenticatedUserService authenticatedUserService;
    Integer totalProductsInCart = 0;
    Double orderTotal = 0.0;
    

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
    public String getCart(Model model) {
        // User user = authenticatedUserService.getCurrentUser();
        
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
        
        User user = authenticatedUserService.getCurrentUser();
        Order cart = new Order();
        
        totalProductsInCart = 0;
        orderTotal = 0.0;
        List<Order> allUserOrders = orderService.getListOrderOfUser(user);
        for (Order order : allUserOrders) {
            String orderStatus = order.getStatus();
            if( orderStatus.equals("pending")) {
                cart = order;
            }
        } 
        for (OrderDetails orderDetail : orderDetailsService.getByOrder(cart)) {
            if(orderDetail.getOrder().getId() == cart.getId()) {
                totalProductsInCart += orderDetail.getQuantity();
                orderTotal += orderDetail.getTotal() * orderDetail.getQuantity();
            }
        }
        
        model.addAttribute("orderDetails", orderDetailsService.getByOrder(cart));
        model.addAttribute("totalProductsInCart", totalProductsInCart);
        model.addAttribute("orderTotal", orderTotal);
        model.addAttribute("cartItem", new CartItem());
        return "cart";
    }

    @GetMapping("/cart/add")
    public String addToCart(Model model, @RequestParam("id") Integer id) {
        User user = authenticatedUserService.getCurrentUser();
        Product product = productService.getProduct(id);
        Order cart = new Order();
        OrderDetails orderDetails = new OrderDetails();
        List<Order> allUserOrders = orderService.getListOrderOfUser(user);
        for (Order order : allUserOrders) {
            String orderStatus = order.getStatus();
            if( orderStatus.equals("pending")) {
                cart = order;
                cart.setOrderDate(new Date());
                
                // orderDetails.setOrder(cart);
                if(orderDetailsService.getByOrderAndProduct(cart.getId(), id) != null) {
                    orderDetails = orderDetailsService.getByOrderAndProduct(cart.getId(), id);
                    orderDetails.setQuantity(orderDetails.getQuantity() + 1);
                    orderDetails.setTotal(0.0);
                    orderDetails.setTotal(orderDetails.getTotal() + product.getPrice());
                    orderDetailsService.save(orderDetails);
                    // model.addAttribute("orderDetails", orderDetailsService.getByOrder(order));
                } else {
                    // orderDetails = new OrderDetails(); 
                    // orderDetails = orderDetailsService.getByOrderAndProduct(cart.getId(), id);
                    orderDetails.setOrder(cart);
                    orderDetails.setProduct(product);
                    orderDetails.setQuantity(1); 
                    orderDetails.setTotal(product.getPrice());
                    orderDetailsService.save(orderDetails);
                    // model.addAttribute("orderDetails", orderDetailsService.getByOrder(order));
                }   
            } else {
                // cart = new Order();
                cart.setOrderDate(new Date());
                cart.setStatus("pending");
                cart.setUser(user);
                orderService.saveOrder(cart);
                orderDetails = new OrderDetails(); 
                orderDetails.setOrder(cart);
                orderDetails.setProduct(product);
                orderDetails.setQuantity(1); 
                orderDetails.setTotal(product.getPrice());
                orderDetailsService.save(orderDetails);
                // model.addAttribute("orderDetails", orderDetailsService.getByOrder(order));
            }
        }

        totalProductsInCart = 0;
        orderTotal = 0.0;
        
        for (OrderDetails orderDetail : orderDetailsService.getByOrder(cart)) {
            if(orderDetail.getOrder().getId() == cart.getId()) {
                totalProductsInCart += orderDetail.getQuantity();
                orderTotal += orderDetail.getTotal();
            }
        }

        model.addAttribute("orderDetails", orderDetailsService.getByOrder(cart));
        model.addAttribute("totalProductsInCart", totalProductsInCart);
        model.addAttribute("orderTotal", orderTotal);
           
        return "redirect:/cart";
    }

    @Transactional
    @GetMapping("/cart/remove")
    public String removeItemFromCart(Model model, @RequestParam("id") Integer id) {
        orderDetailsService.deleteProductFromCart(id);
        return "redirect:/cart";
    }
   


    @Transactional
    @PostMapping("/cart")
    public String updateCart(@ModelAttribute CartItem cartItem, BindingResult result)  {
        // Integer cartItemOrderDetailsId = cartItem.getId();
        // if(orderDetailsService.getByOrderDetailsId(cartItem.getId()) == null) {
        //     return "cart";
        // }
        OrderDetails product = orderDetailsService.getByOrderDetailsId(cartItem.getId());
        product.setQuantity(cartItem.getQuantity());
        if(product.getQuantity() == 0) {
            orderDetailsService.deleteProductFromCart(cartItem.getId());
        } else {
            orderDetailsService.save(product);
        }
     
        return "redirect:/cart";
    }

    @GetMapping("/cart/checkout")
    public String checkoutCart(Model model)  {
        User user = authenticatedUserService.getCurrentUser();
        Order cart = new Order();
        
        totalProductsInCart = 0;
        orderTotal = 0.0;
        List<Order> allUserOrders = orderService.getListOrderOfUser(user);
        for (Order order : allUserOrders) {
            String orderStatus = order.getStatus();
            if( orderStatus.equals("pending")) {
                cart = order;
            }
        } 
        for (OrderDetails orderDetail : orderDetailsService.getByOrder(cart)) {
            if(orderDetail.getOrder().getId() == cart.getId()) {
                totalProductsInCart += orderDetail.getQuantity();
                orderTotal += orderDetail.getTotal() * orderDetail.getQuantity();
            }
        }
        
        model.addAttribute("orderDetails", orderDetailsService.getByOrder(cart));
        model.addAttribute("totalProductsInCart", totalProductsInCart);
        model.addAttribute("orderTotal", orderTotal);
        model.addAttribute("user", user);
        return "checkout";
    }
}
