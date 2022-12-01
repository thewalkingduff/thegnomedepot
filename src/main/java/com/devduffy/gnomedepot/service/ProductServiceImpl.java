package com.devduffy.gnomedepot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.devduffy.gnomedepot.entity.Product;
import com.devduffy.gnomedepot.exception.ProductNotFoundException;
import com.devduffy.gnomedepot.repository.ProductRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService{
    ProductRepository productRepository;

    @Override
    public Product getProduct(Integer id) {
        Optional<Product> product = productRepository.findById(id);
        return unwrapProduct(product, id);
    }

    @Override
    public List<Product> getProducts() {
        return (List<Product>)productRepository.findAll();
    }

    static Product unwrapProduct(Optional<Product> entity, Integer id) {
        if (entity.isPresent()) return entity.get();
        else throw new ProductNotFoundException(id);
    }
}
