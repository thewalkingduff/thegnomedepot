package com.devduffy.gnomedepot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devduffy.gnomedepot.Constants;
import com.devduffy.gnomedepot.entity.Order;
import com.devduffy.gnomedepot.entity.OrderDetails;
import com.devduffy.gnomedepot.entity.User;
import com.devduffy.gnomedepot.exception.OrderNotFoundException;
import com.devduffy.gnomedepot.repository.OrderDetailsRepository;
import com.devduffy.gnomedepot.service.OrderDetailsService;
import com.devduffy.gnomedepot.service.Product;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class OrderDetailsServiceImpl implements OrderDetailsService{

    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    @Override
    public void submitOrderDetails(Order order, User user) {
        OrderDetails orderDetails = new OrderDetails();
      
        orderDetails.setQuantity(3);
        orderDetails.setTotal(57.3);
        if(order.getId() == null || order.getId() == Constants.NOT_FOUND) {
            throw new OrderNotFoundException(order.getId());
        } 
        orderDetailsRepository.save(orderDetails);
    }

  

    @Override
    public void save(OrderDetails orderDetails) {
         orderDetailsRepository.save(orderDetails);   
    }

    @Override
    public List<OrderDetails> getByOrder(Order order) {
        return orderDetailsRepository.findByOrder(order);
    }



    @Override
    public OrderDetails getByOrderAndProduct(Integer orderId, Integer productId) {

        return orderDetailsRepository.findByOrderAndProduct(orderId, productId);
    }



   



   

   

    // @Override
    // public List<Product> getAllProducts(Order order) {
    //     List<Product> cartItems = orderDetailsRepository.findAllProduct();
    //     return cartItems.stream()
    //         .filter(e -> e.getOrder());
    // }

    // @Override
    // public List<Product> getAllProducts() {
    //     // TODO Auto-generated method stub
    //     return orderDetailsRepository.findAllProduct();
    // }

    // @Override
    // public Product getProduct() {
    //     return orderDetailsRepository.findProduct();
    // }

   
}
