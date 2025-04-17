package com.example.demo.Exceptions;

public class AuthorNotFoundException extends RuntimeException {
    public AuthorNotFoundException(int id) {
        super("Author not found with ID: " + id);
    }

    public AuthorNotFoundException(String message) {
        super(message);
    }
}
