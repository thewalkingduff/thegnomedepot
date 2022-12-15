package com.devduffy.gnomedepot.service;

import java.util.List;
import java.util.Set;

import com.devduffy.gnomedepot.entity.Order;
import com.devduffy.gnomedepot.entity.User;

public interface UserService {
    User getUser(Integer id);
    User saveUser(User user);
    void deleteUser(Long id);
    List<User> getUsers();
    List<Order> getOrders(Integer id);
    User getUserByEmail(String email);
    Boolean isUserAuthenticated(String email);
}
