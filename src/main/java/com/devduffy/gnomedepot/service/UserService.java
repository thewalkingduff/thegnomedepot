package com.devduffy.gnomedepot.service;

import java.util.List;
import java.util.Set;

import com.devduffy.gnomedepot.entity.Order;
import com.devduffy.gnomedepot.entity.User;

public interface UserService {
    User getUser(Integer id);
    User saveUser(User user);
    void deleteUser(Integer id);
    List<User> getUsers();
    Set<Order> getOrders(Integer id);
}
