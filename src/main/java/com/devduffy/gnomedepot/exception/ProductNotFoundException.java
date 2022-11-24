package com.devduffy.gnomedepot.exception;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(Integer id) {
        super("The product id '" + id + "' does not exist in our records");
    }
}
