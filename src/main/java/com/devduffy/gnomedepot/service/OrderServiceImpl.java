package com.devduffy.gnomedepot.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.devduffy.gnomedepot.Constants;
import com.devduffy.gnomedepot.entity.Order;
import com.devduffy.gnomedepot.entity.Product;
import com.devduffy.gnomedepot.entity.User;
import com.devduffy.gnomedepot.exception.OrderNotFoundException;
import com.devduffy.gnomedepot.repository.OrderRepository;
import com.devduffy.gnomedepot.repository.ProductRepository;
import com.devduffy.gnomedepot.repository.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    
    OrderRepository orderRepository;
    ProductRepository productRepository;
    UserRepository userRepository;

    @Override
    public Order saveOrder(Order order) {
        if(order.getId() == null || order.getId() == Constants.NOT_FOUND) {
            throw new OrderNotFoundException(order.getId());
        } 
        return orderRepository.save(order);
    }

    @Override
    public Order createOrder(Order order) {
        User user = userRepository.findByUsername(order.getUser().getUsername());
        order.setUser(user);
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrdersOfUser(Integer id) {
        return orderRepository.findByUserId(id);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

	@Override
	public void deleteOrder(Order order) {
        orderRepository.delete(order);	
	}

	@Override
	public void addToCart(Product product, Order order, User user) {
        order.setUser(user);
        order.setStatus("processing");
		orderRepository.save(order);	
	}

	@Override
	public Order getOrderByOrderId(Integer orderId) {
		// TODO Auto-generated method stub
		return null;
	}
}
