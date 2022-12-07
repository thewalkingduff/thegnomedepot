package com.devduffy.gnomedepot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.devduffy.gnomedepot.entity.Order;
import com.devduffy.gnomedepot.entity.OrderDetails;
import com.devduffy.gnomedepot.entity.Product;

public interface OrderDetailsRepository extends CrudRepository<OrderDetails, Integer>{
    // List<Order> findByProductId(Integer productId);
    // List<Order> findByName(String name);
    // List<Order> findByCategory(String category);
    // List<com.devduffy.gnomedepot.service.Product> findAllProduct();
    // com.devduffy.gnomedepot.service.Product findProduct();
    List<OrderDetails> findByOrder(Order order);

    @Query(value = "select * from orderdetails where orders_id = :orderId and products_id = :productId", nativeQuery = true)
    OrderDetails findByOrderAndProduct(Integer orderId, Integer productId);
    
}

