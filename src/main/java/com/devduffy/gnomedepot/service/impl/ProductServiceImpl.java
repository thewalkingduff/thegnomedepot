package com.devduffy.gnomedepot.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.devduffy.gnomedepot.entity.Product;
import com.devduffy.gnomedepot.exception.ProductNotFoundException;
import com.devduffy.gnomedepot.repository.ProductRepository;
import com.devduffy.gnomedepot.service.ProductService;

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
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    static Product unwrapProduct(Optional<Product> entity, Integer id) {
        if (entity.isPresent()) return entity.get();
        else throw new ProductNotFoundException(id);
    }

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);  
    }

    @Override
    public List<Product> getByNameContaining(String name) {
        List<Product> products = productRepository.findByNameIgnoreCaseContaining(name);
        return products;
    }

    // @Override
    // public List<Product> getByName(String name) {
    //     return productRepository.findByName(name);
    // }
}
