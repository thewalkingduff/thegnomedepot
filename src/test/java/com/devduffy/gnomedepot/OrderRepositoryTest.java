package com.devduffy.gnomedepot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.devduffy.gnomedepot.repository.OrderRepository;

public class OrderRepositoryTest {
    @Autowired
    private OrderRepository orderRepository;

    // @Test
    // void findByOrderIdTest() {
    //     assertEquals(13.9434, orderRepository.findByOrderId(30).getTotalAmount());
    // }

}
