package com.example.spring.Service;

import com.example.spring.DTO.BookDTO;
import com.example.spring.Exceptions.BookNotFoundException;
import jakarta.transaction.Transactional;

import java.util.List;

public interface BookService {
    @Transactional
    BookDTO AddBook(BookDTO bookDTO);

    public List<BookDTO> getBooks();

    BookDTO GetBookById(int id) throws BookNotFoundException;

    @Transactional
    BookDTO UpdateBook(BookDTO bookDTO);

    @Transactional
    void DeleteBook(int id);
}
