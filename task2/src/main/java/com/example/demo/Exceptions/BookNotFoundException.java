package com.example.demo.Exceptions;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String message) {
        super(message);
    }

    public BookNotFoundException(int id) {
        super("Book with ID " + id + " not found");
    }
    public BookNotFoundException(int idBook , int idAuthor) {
        super("Book with ID " + idBook + "for Author with id "+ idAuthor +"  not found");
    }
}