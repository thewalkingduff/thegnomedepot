package com.devduffy.gnomedepot.service;

import java.util.List;

import com.devduffy.gnomedepot.entity.Order;
import com.devduffy.gnomedepot.entity.OrderDetails;
import com.devduffy.gnomedepot.entity.User;

public interface OrderDetailsService {
    void submitOrderDetails(Order order, User user);
    // List<Product> getAllProducts();
    // Product getProduct();
}
