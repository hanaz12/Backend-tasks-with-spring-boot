package com.example.demo.Exceptions;

public class OrderNotFoundException extends RuntimeException {

        public OrderNotFoundException(int id) {
            super("Category not found with ID: " + id);
        }

        public OrderNotFoundException(String message) {
            super(message);
        }
    }

