package com.example.demo.Exceptions;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String message) {
        super(message);
    }

    public BookNotFoundException(int id) {
        super("Book with ID " + id + " not found");
    }
}