package com.devduffy.gnomedepot.web;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.devduffy.gnomedepot.dto.CartItem;
import com.devduffy.gnomedepot.dto.ProductQuantityDTO;
import com.devduffy.gnomedepot.entity.Order;
import com.devduffy.gnomedepot.entity.OrderDetails;
import com.devduffy.gnomedepot.entity.Product;
import com.devduffy.gnomedepot.entity.User;
import com.devduffy.gnomedepot.security.AuthenticatedUserService;
import com.devduffy.gnomedepot.service.OrderDetailsService;
import com.devduffy.gnomedepot.service.OrderService;
import com.devduffy.gnomedepot.service.ProductService;
import com.devduffy.gnomedepot.service.UserService;

@Controller
public class OrderController {

    OrderService orderService;
    OrderDetailsService orderDetailsService;
    ProductService productService;
    UserService userService;
    AuthenticatedUserService authenticatedUserService;
    Integer totalProductsInCart = 0;
    Double orderTotal = 0.0;

    public OrderController(OrderService orderService, ProductService productService, UserService userService, AuthenticatedUserService authenticatedUserService, OrderDetailsService orderDetailsService) {
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
        List<Product> productsInCart = new ArrayList<>();
        
        totalProductsInCart = 0;
        orderTotal = 0.0;
        orderService.setFieldsIfNewOrder(order, user);

        List<OrderDetails> orderDetails = orderDetailsService.getByOrder(order);
        
        if (!(orderDetails.isEmpty())) {
            for (OrderDetails orderDetail : orderDetails) {
                totalProductsInCart += orderDetail.getQuantity();
                orderTotal += orderDetail.getTotal();
                productsInCart.add(orderDetail.getProduct());
            }
        }
        
        if(!(orderDetails.isEmpty())) {
            List<Product> similarProducts = new ArrayList<Product>();
            similarProducts.clear();
            for (int i = 0; i < orderDetails.size(); i++) {
                Product similarProduct = productService.getSimilarProducts(orderDetails.get(i).getProduct().getCategory().substring(0, 3), orderDetails.get(i).getProduct().getId());
                if(similarProduct != null && (!(similarProducts.contains(similarProduct))) && (!(productsInCart.contains(similarProduct)))) {
                    similarProducts.add(similarProduct);
                }  
            }
            model.addAttribute("similarProducts", similarProducts);
        }
          
        order.setTotalAmount(orderTotal);
        orderService.saveOrder(order);
        
        model.addAttribute("orderDetails", orderDetails);
        model.addAttribute("order", order);
        model.addAttribute("totalProductsInCart", totalProductsInCart);
        model.addAttribute("orderTotal", orderTotal);
        model.addAttribute("cartItem", new CartItem());
        return "cart";
    }

    @PostMapping("/order/add")
    public String addToOrder(Model model, @ModelAttribute ProductQuantityDTO productQuantityDTO, BindingResult result) {
        User user = authenticatedUserService.getCurrentUser();
        Product product = productService.getProduct(productQuantityDTO.getId());
        Order order = orderService.getCurrentOrderOrNewOrder(user);
        OrderDetails orderDetails = new OrderDetails();

        if (orderDetailsService.getByOrderAndProduct(order.getId(), productQuantityDTO.getId()) != null) {
            orderDetails = orderDetailsService.getByOrderAndProduct(order.getId(), productQuantityDTO.getId());
        }

        if (order.getId() == null) {
            orderService.setFieldsIfNewOrder(order, user);
            orderDetails = orderDetailsService.createOrderDetails(productQuantityDTO);
        } else if (orderDetails.getId() == null) {
            orderDetails = orderDetailsService.createOrderDetails(productQuantityDTO);
        } else {
            orderDetails.setQuantity(orderDetails.getQuantity() + productQuantityDTO.getQuantity());
            orderDetails.setTotal(product.getPrice() * orderDetails.getQuantity());
        }

        orderDetailsService.save(orderDetails);

        return "redirect:/order/current";
    }

    @Transactional
    @GetMapping("/order/delete")
    public String removeItemFromorder(Model model, @RequestParam("id") Integer id) {
        orderDetailsService.deleteItemFromOrder(id);
        return "redirect:/order/current";
    }

    @Transactional
    @PostMapping("/order/update")
    public String updateOrder(@ModelAttribute CartItem cartItem, BindingResult result) {
        OrderDetails product = orderDetailsService.getByOrderDetailsId(cartItem.getId());
        product.setQuantity(cartItem.getQuantity());
        product.setTotal(product.getQuantity() * product.getProduct().getPrice());
        if (product.getQuantity() == 0) {
            orderDetailsService.deleteItemFromOrder(cartItem.getId());
        } else {
            orderDetailsService.save(product);
        }
        return "redirect:/order/current";
    }

    @Transactional
    @PostMapping("/order/checkout/update")
    public String updateCheckoutOrder(Model model, @ModelAttribute CartItem cartItem, BindingResult result, RedirectAttributes redirectAttributes) {
        OrderDetails item = orderDetailsService.getByOrderDetailsId(cartItem.getId());
        item.setQuantity(cartItem.getQuantity());
        item.setTotal(item.getProduct().getPrice() * cartItem.getQuantity());
        if (item.getQuantity() == 0) {
            orderDetailsService.deleteItemFromOrder(cartItem.getId());
        } else {
            orderDetailsService.save(item);
        }
        redirectAttributes.addAttribute("id",
                orderService.getCurrentOrderOrNewOrder(authenticatedUserService.getCurrentUser()).getId());
        return "redirect:/order/checkout";
    }

    @GetMapping("/order/checkout")
    public String checkoutorder(Model model, @RequestParam("id") Integer id) {
        User user = authenticatedUserService.getCurrentUser();
        Order order = orderService.getOrderById(id);

        totalProductsInCart = 0;
        orderTotal = 0.0;

        List<OrderDetails> itemsInOrder = orderDetailsService.getByOrder(order);
        for (OrderDetails item : itemsInOrder) {
        totalProductsInCart += item.getQuantity();
        orderTotal += item.getTotal();
        }

        order.setTotalAmount(orderTotal);
        orderService.saveOrder(order);

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
    public String updateorder(Model model, @RequestParam("id") Integer id) {
        if (totalProductsInCart > 0) {
            Order order = orderService.finalizeOrder(id);
            List<OrderDetails> orderDetailsItem = orderDetailsService.getByOrder(order);
            System.out.println(orderDetailsItem);
            productService.updateProductQuantityInStock(orderDetailsItem);
        } else {
            return "checkout";
        }
        return "success-order";
    }
}
