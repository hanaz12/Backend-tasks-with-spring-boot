package com.example.demo.Service;


import com.example.demo.DTO.BookDTO;
import com.example.demo.Model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BookService {
    public boolean saveBook(BookDTO bookDTO);
    public BookDTO getBookById(int id);
    public List<BookDTO> getAllBooks();
    public boolean deleteBookById(int id);
    public BookDTO updateBook(BookDTO bookDTO , int id);
}
