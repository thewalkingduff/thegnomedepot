package com.devduffy.gnomedepot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.devduffy.gnomedepot.entity.Order;
import com.devduffy.gnomedepot.entity.OrderDetails;
import com.devduffy.gnomedepot.entity.Product;

public interface OrderDetailsRepository extends CrudRepository<OrderDetails, Long> {

    List<OrderDetails> findByOrder(Order order);

    @Query(value = "select * from orderdetails where orders_id = :orderId and products_id = :productId", nativeQuery = true)
    OrderDetails findByOrderAndProduct(Integer orderId, Integer productId);

    void deleteById(Integer id);

    @Query(value = "select * from orderdetails where id = :id", nativeQuery = true)
    OrderDetails findByOrderDetailsId(Integer id);

   
   

}
