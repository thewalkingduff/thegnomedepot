package com.devduffy.gnomedepot.service;

import java.util.Optional;

import com.devduffy.gnomedepot.entity.Product;
import com.devduffy.gnomedepot.exception.ProductNotFoundException;
import com.devduffy.gnomedepot.repository.ProductRepository;

public class ProductServiceImpl implements ProductService{
    ProductRepository productRepository;

    @Override
    public Product getProduct(Integer id) {
        Optional<Product> product = productRepository.findById(id);
        return unwrapProduct(product, id);
    }

    static Product unwrapProduct(Optional<Product> product, Integer id) {
        if (product.isPresent()) return product.get();
        else throw new ProductNotFoundException(id);
    }
}
