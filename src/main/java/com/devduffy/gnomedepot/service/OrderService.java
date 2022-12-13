package com.devduffy.gnomedepot.service;

import java.util.List;
import java.util.Map;

import com.devduffy.gnomedepot.entity.Order;
import com.devduffy.gnomedepot.entity.Product;
import com.devduffy.gnomedepot.entity.User;

public interface OrderService {
    Order createOrder(Order order);
    Order getOrderById(Integer id);
    void deleteOrder(Order order);
    List<Order> getListOrderOfUser(User user);
    List<Order> getAllOrders();
    void addToCart(Product product, Order order, User user);
    // List<Product> getProductsInOrder(User user, Order orderId);
    Order getOrderByUser(User user);
    void saveOrder(Order order);
    Order getCurrentOrderOrNewOrder(User user);
    void setFieldsIfNewOrder(Order order, User user);
   
   
}
