package com.devduffy.gnomedepot.web;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.devduffy.gnomedepot.Constants;
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

@Slf4j
@Controller
public class OrderController {

    OrderService orderService;
    OrderDetailsService orderDetailsService;
    ProductService productService;
    UserService userService;
    AuthenticatedUserService authenticatedUserService;
    Integer totalProductsInCart = 0;
    Double orderTotal = 0.0;
    
    public OrderController(OrderService orderService, ProductService productService, UserService userService, AuthenticatedUserService authenticatedUserService,
    OrderDetailsService orderDetailsService) {
        this.orderService = orderService;
        this.productService = productService;
        this.userService = userService;
        this.authenticatedUserService = authenticatedUserService;
        this.orderDetailsService = orderDetailsService;
    }

    @GetMapping("/order/current")
    public String getCurrentOrder(Model model) {
        User user = authenticatedUserService.getCurrentUser();
        Order order = orderService.getCurrentOrderOrNewOrder(user);
        
        totalProductsInCart = 0;
        orderTotal = 0.0;

        if(order.getId() == null) {
            order.setOrderDate(new Date());
            order.setStatus("pending");
            order.setUser(user);
            order.setTotalAmount(orderTotal);
            orderService.saveOrder(order);
        }

        if(!(orderDetailsService.getByOrder(order).isEmpty())) {
            for (OrderDetails orderDetail : orderDetailsService.getByOrder(order)) {
                // if(orderDetail.getOrder().getId() == order.getId()) {
                    totalProductsInCart += orderDetail.getQuantity();
                    orderTotal += orderDetail.getTotal() * orderDetail.getQuantity();
                // }
            }
        }

        order.setTotalAmount(orderTotal);
        orderService.saveOrder(order);
        
        model.addAttribute("orderDetails", orderDetailsService.getByOrder(order));
        model.addAttribute("order", order);
        model.addAttribute("totalProductsInCart", totalProductsInCart);
        model.addAttribute("orderTotal", orderTotal);
        model.addAttribute("cartItem", new CartItem());
        return "cart";
    }

    @GetMapping("/order/add")
    public String addToorder(Model model, @RequestParam("id") Integer id) {
        User user = authenticatedUserService.getCurrentUser();
        Product product = productService.getProduct(id);
        Order order = new Order();
        OrderDetails orderDetails = new OrderDetails();
        List<Order> allUserOrders = orderService.getListOrderOfUser(user);
        for (Order o : allUserOrders) {
            String orderStatus = o.getStatus();
            if( orderStatus.equals("pending")) {
                order = o;
                order.setOrderDate(new Date());
                
               // this is checking if product is in order. if it is, add 1 to quantity
                if(orderDetailsService.getByOrderAndProduct(order.getId(), id) != null) {
                    orderDetails = orderDetailsService.getByOrderAndProduct(order.getId(), id);
                    orderDetails.setQuantity(orderDetails.getQuantity() + 1);
                    orderDetails.setTotal(0.0);
                    orderDetails.setTotal(orderDetails.getTotal() + product.getPrice());
                    orderDetailsService.save(orderDetails);
                // else, add product to order and set quantity to 1 
                } else {
                    orderDetails.setOrder(order);
                    orderDetails.setProduct(product);
                    orderDetails.setQuantity(1); 
                    orderDetails.setTotal(product.getPrice());
                    orderDetailsService.save(orderDetails);
                }   
            } 
        }

        if(order.getId() == null) {
            order.setOrderDate(new Date());
            order.setStatus("pending");
            order.setUser(user);
            order.setTotalAmount(orderTotal);
            orderService.saveOrder(order);
            orderDetails = new OrderDetails(); 
            orderDetails.setOrder(order);
            orderDetails.setProduct(product);
            orderDetails.setQuantity(1); 
            orderDetails.setTotal(product.getPrice());
            orderDetailsService.save(orderDetails);
        }
        
        for (OrderDetails orderDetail : orderDetailsService.getByOrder(order)) {
            if(orderDetail.getOrder().getId() == order.getId()) {
                totalProductsInCart += orderDetail.getQuantity();
                orderTotal += orderDetail.getTotal();
            }
        }

        order.setTotalAmount(orderTotal);

        model.addAttribute("orderDetails", orderDetailsService.getByOrder(order));
        model.addAttribute("totalProductsInCart", totalProductsInCart);
        model.addAttribute("orderTotal", orderTotal);
           
        return "redirect:/order/current";
    }

    @Transactional
    @GetMapping("/order/delete")
    public String removeItemFromorder(Model model, @RequestParam("id") Integer id) {
        orderDetailsService.deleteProductFromOrder(id);
        return "redirect:/order/current";
    }
   


    @Transactional
    @PostMapping("/order/update")
    public String updateOrder(@ModelAttribute CartItem cartItem, BindingResult result)  {
        // Integer orderItemOrderDetailsId = orderItem.getId();
        // if(orderDetailsService.getByOrderDetailsId(orderItem.getId()) == null) {
        //     return "order";
        // }
        OrderDetails product = orderDetailsService.getByOrderDetailsId(cartItem.getId());
        product.setQuantity(cartItem.getQuantity());
        if(product.getQuantity() == 0) {
            orderDetailsService.deleteProductFromOrder(cartItem.getId());
        } else {
            orderDetailsService.save(product);
        }
     
        return "redirect:/order/current";
    }

    @Transactional
    @PostMapping("/order/checkout/update")
    public String updateCheckoutOrder(@ModelAttribute CartItem cartItem, BindingResult result, RedirectAttributes redirectAttributes)  {
        // Integer orderItemOrderDetailsId = orderItem.getId();
        // if(orderDetailsService.getByOrderDetailsId(orderItem.getId()) == null) {
        //     return "order";
        // }
        OrderDetails product = orderDetailsService.getByOrderDetailsId(cartItem.getId());
        product.setQuantity(cartItem.getQuantity());
        if(product.getQuantity() == 0) {
            orderDetailsService.deleteProductFromOrder(cartItem.getId());
        } else {
            orderDetailsService.save(product);
        }
        redirectAttributes.addAttribute("id", orderService.getCurrentOrderOrNewOrder(authenticatedUserService.getCurrentUser()).getId());
        return "redirect:/order/checkout";
    }

    @GetMapping("/order/checkout")
    public String checkoutorder(Model model, @RequestParam("id") Integer id)  {
        User user = authenticatedUserService.getCurrentUser();
        Order order = orderService.getOrderById(id);

        totalProductsInCart = 0;
        orderTotal = 0.0;

        List<OrderDetails> itemsInOrder = orderDetailsService.getByOrder(order);

        for (OrderDetails item : itemsInOrder) {
                totalProductsInCart += item.getQuantity();
                orderTotal += item.getTotal() * item.getQuantity();
        }
        model.addAttribute("totalProductsInCart", totalProductsInCart);
        model.addAttribute("user", user);
        model.addAttribute("orderDetails", orderDetailsService.getByOrder(order));
        model.addAttribute("orderTotal", orderTotal);
        model.addAttribute("order", order);
        model.addAttribute("cartItem", new CartItem());
        return "checkout";
    }

    @Transactional
    @GetMapping("/order/submit")
    public String updateorder(Model model, @RequestParam("id") Integer id)  {
        if(totalProductsInCart > 0) {
            Order order = orderService.getOrderById(id);
            order.setOrderDate(new Date());
            order.setStatus("complete");
            order.setTotalAmount(order.getTotalAmount() + Constants.FLAT_SHIPPING_COST + (order.getTotalAmount()*0.06));
            orderService.saveOrder(order);
        } else {
            return "checkout";
        }
        return "success-order";
    } 
}
