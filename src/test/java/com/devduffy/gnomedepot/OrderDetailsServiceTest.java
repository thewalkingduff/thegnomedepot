package com.devduffy.gnomedepot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.devduffy.gnomedepot.entity.Order;
import com.devduffy.gnomedepot.entity.OrderDetails;
import com.devduffy.gnomedepot.entity.Product;
import com.devduffy.gnomedepot.repository.OrderDetailsRepository;
import com.devduffy.gnomedepot.service.impl.OrderDetailsServiceImpl;

import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class OrderDetailsServiceTest {

    @Mock
    private OrderDetailsRepository orderDetailsRepository;

    @InjectMocks
    private OrderDetailsServiceImpl orderDetailsServiceImpl;

    @Test
    public void findByOrderIdTest() {
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setId(67);
        orderDetails.setOrder(new Order());
        orderDetails.setProduct(new Product());
        orderDetails.setQuantity(1);
        orderDetails.setTotal(26.39);

        when(orderDetailsRepository.findByOrderDetailsId(67)).thenReturn(orderDetails);

        OrderDetails result = orderDetailsServiceImpl.getByOrderDetailsId(67);

        assertEquals(orderDetails, result);
    }

}