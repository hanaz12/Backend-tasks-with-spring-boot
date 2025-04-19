package com.example.demo.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandlerForCategory {
    @ExceptionHandler(ParentCategoryNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleParentCategoryNotFoundException(ParentCategoryNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse("Category_NOT_FOUND", ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCategoryNotFoundException(CategoryNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse("Parent Category_NOT_FOUND", ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
    @ExceptionHandler(SubcategoriesFoundException.class)
    public ResponseEntity<ErrorResponse> handleSubcategoriesFoundException(SubcategoriesFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse("subcategories found", ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}
