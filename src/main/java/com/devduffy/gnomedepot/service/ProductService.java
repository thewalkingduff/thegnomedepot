package com.devduffy.gnomedepot.service;

import java.util.List;

import javax.transaction.Transactional;

import com.devduffy.gnomedepot.entity.OrderDetails;
import com.devduffy.gnomedepot.entity.Product;

public interface ProductService {
    Product getProduct(Integer id);
    List<Product> getAllProducts();
    void saveProduct(Product product);
    @Transactional
    List<Product> getByNameContaining(String name);
    void updateProductQuantityInStock(List<OrderDetails> orderDetailsItem);
    Product getSimilarProducts(String category, Integer prodId);
}
 