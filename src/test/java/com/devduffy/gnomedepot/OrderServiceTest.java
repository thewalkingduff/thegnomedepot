package com.devduffy.gnomedepot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.devduffy.gnomedepot.entity.Order;
import com.devduffy.gnomedepot.repository.OrderRepository;
import com.devduffy.gnomedepot.service.impl.OrderServiceImpl;

import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderServiceImpl;

    @Test
    public void findByOrderIdTest() {
        Order order = new Order();
        order.setId(23);
        order.setOrderDate(new Date());
        order.setStatus("complete");
        order.setUser(new com.devduffy.gnomedepot.entity.User());
        order.setTotalAmount(64.1662);

        when(orderRepository.findByOrderId(23)).thenReturn(order) ;

        Double result = orderServiceImpl.getOrderById(23).getTotalAmount();

        assertEquals(64.1662, result);
    }

}