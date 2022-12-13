package com.devduffy.gnomedepot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.devduffy.gnomedepot.entity.Order;
import com.devduffy.gnomedepot.repository.OrderRepository;



@DataJpaTest
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    // @Test
    // public void getByOrderIdTest() {
    //     Order order = orderRepository.findByOrderId(5);
    //     Assertions.assertThat(order.getId()).isEqualTo(5);
    // }

}
