package com.example.demo.Exceptions;

import java.io.Serializable;

public class ParentCategoryNotFoundException extends RuntimeException {

        public ParentCategoryNotFoundException(int id) {
            super("Category not found with ID: " + id);
        }

        public ParentCategoryNotFoundException(String message) {
            super(message);
        }
    }
