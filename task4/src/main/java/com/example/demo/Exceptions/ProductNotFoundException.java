package com.example.demo.Exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(int id) {
        super("product id " + id + " not found");
    }
    public ProductNotFoundException(String message) {
        super(message);
    }
}
