package com.devduffy.gnomedepot.exception;

public class OrderNotFoundException extends RuntimeException{
    
    public OrderNotFoundException(Integer orderId) {
        super("The order with the order id: '" + orderId + "' does not exist in our records");
    }
}
