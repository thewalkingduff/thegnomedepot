package com.devduffy.gnomedepot.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.devduffy.gnomedepot.Constants;
import com.devduffy.gnomedepot.entity.Order;
import com.devduffy.gnomedepot.entity.OrderDetails;
import com.devduffy.gnomedepot.entity.User;
import com.devduffy.gnomedepot.exception.OrderNotFoundException;
import com.devduffy.gnomedepot.repository.OrderDetailsRepository;

public class OrderDetailsServiceImpl implements OrderDetailsService{

    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    @Override
    public void submitOrderDetails(Order order, User user) {
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setOrder(order);
        orderDetails.setQuantity(3);
        orderDetails.setTotal(57.3);
        if(order.getId() == null || order.getId() == Constants.NOT_FOUND) {
            throw new OrderNotFoundException(order.getId());
        } 
        orderDetailsRepository.save(orderDetails);
    }
}
