package com.webshop.webshopbackend.exception;

public class NotFound extends RuntimeException {
    public NotFound(String resource, String id) {
        super(String.format("%s with id %s not found.", resource, id));
    }
}
