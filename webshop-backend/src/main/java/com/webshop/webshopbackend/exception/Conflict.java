package com.webshop.webshopbackend.exception;

public class Conflict extends RuntimeException {

    public Conflict(String resource, String attribute) {
        super(String.format("%s with that %s already exists", resource, attribute));
    }
}
