package com.devduffy.gnomedepot.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devduffy.gnomedepot.Constants;
import com.devduffy.gnomedepot.entity.Order;
import com.devduffy.gnomedepot.entity.Product;
import com.devduffy.gnomedepot.entity.User;
import com.devduffy.gnomedepot.exception.OrderNotFoundException;
import com.devduffy.gnomedepot.repository.OrderRepository;
import com.devduffy.gnomedepot.repository.ProductRepository;
import com.devduffy.gnomedepot.repository.UserRepository;
import com.devduffy.gnomedepot.service.OrderService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Order createOrder(Order order) {
        User user = userRepository.findByUsername(order.getUser().getUsername());
        order.setUser(user);
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        System.out.println("orderRepo: " + orderRepository.findAll());
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
        order.setOrderDate(new Date());
        
		// orderRepository.save(order);	
	}

	

    @Override
    public Order getOrderByUser(User user) {
        return orderRepository.findOrderByUser(user);
    }

    @Override
    public void saveOrder(Order order) {
        orderRepository.save(order);    
    }

    @Override
    public List<Order> getListOrderOfUser(User user) {
        return orderRepository.findByUser(user);
    }

    @Override
    public Order getOrderById(Integer id) {
        return orderRepository.findByOrderId(id);
    }

    @Override
    public Order getCurrentOrderOrNewOrder(User user) {
        List<Order> allUserOrders = getListOrderOfUser(user);
        // allUserOrders.stream()
        // .filter(order -> order.getStatus().equals("pending"))
        // .findAny();
          
        for (Order o : allUserOrders) {
            String orderStatus = o.getStatus();
            if( orderStatus.equals("pending")) {
                return o;
            }  
        } 
        return new Order();
    }

    @Override
    public void setFieldsIfNewOrder(Order order, User user) {
        if (order.getId() == null) {
            order.setOrderDate(new Date());
            order.setStatus("pending");
            order.setUser(user);
            order.setTotalAmount(Constants.ZERO_DOUBLE);
            orderRepository.save(order);
        }
    }

    

    

    

    
    // @Override
    // public List<Product> getProductsInOrder(User user, Order orderId) {
    //     // TODO Auto-generated method stub
    //     return orderRepository.findByProducts(user, orderId);
    // }
}
