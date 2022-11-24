package com.devduffy.gnomedepot.exception;

public class OrderNotFoundException extends RuntimeException{
    
    public OrderNotFoundException(Integer userId ,Integer orderId) {
        super("The order with user id: '" + userId + "' and order id: '" + orderId + "' does not exist in our records");
    }
}
