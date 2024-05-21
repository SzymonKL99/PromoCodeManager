package com.example.promocodemanager.exceptions;

public class PromoCodeNotFoundException extends RuntimeException {

    public PromoCodeNotFoundException(String message) {
        super(message);
    }
}
