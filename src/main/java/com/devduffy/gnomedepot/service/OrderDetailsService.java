package com.devduffy.gnomedepot.service;

import com.devduffy.gnomedepot.entity.Order;
import com.devduffy.gnomedepot.entity.OrderDetails;
import com.devduffy.gnomedepot.entity.User;

public interface OrderDetailsService {
    void submitOrderDetails(Order order, User user);
}
