package com.example.demo.Service;

import com.example.demo.DTO.BookDTO;
import com.example.demo.Exceptions.AuthorNotFoundException;
import com.example.demo.Exceptions.BookNotFoundException;
import jakarta.transaction.Transactional;

import java.util.List;

public interface BookService {



     @Transactional
     boolean addBook(BookDTO bookDTO, int authorId) throws AuthorNotFoundException, IllegalArgumentException;

     List<BookDTO> getBooksByAuthor(int authorId) throws AuthorNotFoundException;

     BookDTO getBookByIdAndAuthorId(int bookId, int authorId)
             throws BookNotFoundException, AuthorNotFoundException;

     BookDTO updateBookByAuthorId(BookDTO bookDTO, int bookId, int authorId)
             throws BookNotFoundException, AuthorNotFoundException;

     boolean deleteBookByAuthorId(int bookId, int authorId)
             throws BookNotFoundException, AuthorNotFoundException;
}
