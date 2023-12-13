package com.jmorales.springbootreact.Exception;

public class CardNotFoundException extends RuntimeException{

    public CardNotFoundException(String message){
        super(message);
    }

}
