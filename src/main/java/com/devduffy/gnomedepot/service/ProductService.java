package com.devduffy.gnomedepot.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.devduffy.gnomedepot.entity.Product;

public interface ProductService {
    Product getProduct(Integer id);
    List<Product> getProducts();
    void saveProduct(Product product);
    // @Transactional
    // List<Product> getByName(String name);
    @Transactional
    List<Product> getByNameContaining(String name);

}
