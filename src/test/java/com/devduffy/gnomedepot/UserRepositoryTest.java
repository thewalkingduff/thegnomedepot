package com.devduffy.gnomedepot;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.devduffy.gnomedepot.entity.Order;
import com.devduffy.gnomedepot.entity.User;
import com.devduffy.gnomedepot.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserRepositoryTest {
    @Mock
    private UserRepository userRepository;

    @Test
    public void findOrderByUserFromRepoTest() {
        User user = new User();
        user.setId(11);
        user.setUsername("ericcole");
        user.setPassword("password");
        user.setEmail("ericcole@gmail.com");
        user.setCreateDate(new Date());
        
        List<Order> orders = new ArrayList<>();
        Order order = new Order();
        orders.add(order);
        when(userRepository.findByEmail("ericcole@gmail.com")).thenReturn((user));

        User result = userRepository.findByEmail("ericcole@gmail.com");

        assertEquals(user, result);
    }
}