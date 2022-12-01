package com.devduffy.gnomedepot.service;

import java.util.List;
import java.util.Map;

import com.devduffy.gnomedepot.entity.Order;
import com.devduffy.gnomedepot.entity.Product;
import com.devduffy.gnomedepot.entity.User;

public interface OrderService {
    Order createOrder(Order order);
    Order getOrderByOrderId(Integer orderId);
    Order saveOrder(Order order);
    void deleteOrder(Order order);
    List<Order> getAllOrdersOfUser(Integer id);
    List<Order> getAllOrders();
    void addToCart(Product product, Order order, User user);
}
