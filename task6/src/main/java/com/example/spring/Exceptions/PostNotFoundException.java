package com.example.spring.Exceptions;

public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException() {
        super();
    }
    public PostNotFoundException(String message) {
        super(message);
    }
    public PostNotFoundException(int id) {
        super("Post id " + id + " not found");
    }
}
