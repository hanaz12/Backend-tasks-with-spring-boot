package com.example.demo.Exceptions;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(int id) {
        super("Category id " + id + " not found");
    }
    public CategoryNotFoundException(String message) {
        super(message);
    }
}
