package com.devduffy.gnomedepot;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.devduffy.gnomedepot.entity.Order;
import com.devduffy.gnomedepot.entity.User;
import com.devduffy.gnomedepot.repository.OrderRepository;
import com.devduffy.gnomedepot.repository.UserRepository;
import com.devduffy.gnomedepot.service.impl.OrderServiceImpl;
import com.devduffy.gnomedepot.service.impl.UserServiceImpl;



@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {
    
    // @Mock
    // private OrderRepository orderRepository;

    // @Mock
    // private UserRepository userRepository;

    // @InjectMocks
    // private OrderServiceImpl orderServiceImpl;

    // @InjectMocks
    // private UserServiceImpl userServiceImpl;

    // @Test
    // public void createOrderTest() {
    //     Order order = new Order();
    //     order.setId(40);
    //     order.setOrderDate(new Date());
    //     order.setStatus("complete");
    //     order.setShippedDate(null);
    //     order.setTotalAmount(77.52);
    //     order.setUser(new User());

    //     Order newOrder = orderServiceImpl.createOrder(order);

    //     assertEquals(40, newOrder.getId());

    // }

    
}