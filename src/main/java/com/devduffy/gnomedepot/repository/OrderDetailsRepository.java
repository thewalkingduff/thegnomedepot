package com.devduffy.gnomedepot.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.devduffy.gnomedepot.entity.Order;

public interface OrderDetailsRepository extends CrudRepository<Order, Integer>{
    // List<Order> findByProductId(Integer productId);
    // List<Order> findByName(String name);
    // List<Order> findByCategory(String category);
}

