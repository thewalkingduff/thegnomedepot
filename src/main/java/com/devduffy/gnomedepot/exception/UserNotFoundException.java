package com.devduffy.gnomedepot.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(Integer id) {
        super("The student id '" + id + "' does not exist in our records");
    }
    
}
