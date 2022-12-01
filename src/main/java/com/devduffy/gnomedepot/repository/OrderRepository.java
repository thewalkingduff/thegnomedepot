package com.devduffy.gnomedepot.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.devduffy.gnomedepot.entity.Order;

public interface OrderRepository extends CrudRepository<Order, Integer>{
    List<Order> findByUserId(Integer userId);
    Optional<Order> findById(Integer id);
    void deleteById(Integer orderId);
    List<Order> findAll();
}
