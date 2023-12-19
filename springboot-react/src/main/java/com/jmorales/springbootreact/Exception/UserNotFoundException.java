package com.jmorales.springbootreact.Exception;

public class UserNotFoundException extends RuntimeException{
    UserNotFoundException(String message) {
        super(message);
    }
}
