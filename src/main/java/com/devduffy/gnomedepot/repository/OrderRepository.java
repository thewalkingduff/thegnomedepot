package com.devduffy.gnomedepot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.devduffy.gnomedepot.entity.Order;
import com.devduffy.gnomedepot.entity.User;

public interface OrderRepository extends CrudRepository<Order, Long>{

    void deleteById(Integer orderId);
    List<Order> findAll();
    Order findOrderByUser(User user);
    List<Order> findByUser(User user);

    @Query(value = "select * from orders where id = :id", nativeQuery = true)
    Order findByOrderId(Integer id);
    
}
