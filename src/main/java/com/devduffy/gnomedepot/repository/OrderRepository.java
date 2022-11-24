package com.devduffy.gnomedepot.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.devduffy.gnomedepot.entity.Order;

public interface OrderRepository extends CrudRepository<Order, Integer>{
    List<Order> findByUserId(Integer userId);
    List<Order> findByProductId(Integer productId);
    List<Order> findByProductName(String name);
    List<Order> findByProductCategory(String category);
    @Transactional
    void deleteByOrderId(Integer orderId);
}
