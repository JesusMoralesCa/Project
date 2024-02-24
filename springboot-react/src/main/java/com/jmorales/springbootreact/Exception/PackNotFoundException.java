package com.jmorales.springbootreact.Exception;

public class PackNotFoundException extends RuntimeException{
    public PackNotFoundException(String message) {
        super(message);
    }
}
