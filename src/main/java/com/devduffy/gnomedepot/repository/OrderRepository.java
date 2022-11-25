package com.devduffy.gnomedepot.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.devduffy.gnomedepot.entity.Order;

public interface OrderRepository extends CrudRepository<Order, Integer>{
    List<Order> findByUserId(Integer userId);
    Optional<Order> findById(Integer id);
    // List<Order> findOrdersByCategory(String category);
    @Transactional
    void deleteById(Integer orderId);
}
