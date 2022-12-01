package com.devduffy.gnomedepot.service;

import java.util.List;

import com.devduffy.gnomedepot.entity.Product;

public interface ProductService {
    Product getProduct(Integer id);
    List<Product> getProducts();
}
