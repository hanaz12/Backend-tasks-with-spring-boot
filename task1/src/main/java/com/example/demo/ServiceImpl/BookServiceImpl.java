package com.example.demo.ServiceImpl;


import com.example.demo.DTO.BookDTO;
import com.example.demo.Model.Book;
import com.example.demo.Repository.BookRepo;
import com.example.demo.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepo bookRepo;



    @Override
    public boolean saveBook(BookDTO bookDTO) {
        try{
       Book book = new Book();
       book.setAuthor(bookDTO.getAuthor());
       book.setTitle(bookDTO.getTitle());
       book.setPublishedDate(bookDTO.getPublishedDate());
       bookRepo.save(book);
       return true;

    } catch (Exception e) {
            return false;
        }
    }

    @Override
    public BookDTO getBookById(int id) {
        Optional<Book> bookOptional = bookRepo.findById(id);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            BookDTO bookDTO = new BookDTO();
            bookDTO.setId(book.getId());
            bookDTO.setTitle(book.getTitle());
            bookDTO.setAuthor(book.getAuthor());
            bookDTO.setPublishedDate(book.getPublishedDate());
            return bookDTO;
        }
        return null;
    }

    @Override
    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepo.findAll();
        return books.stream()
                .map(book -> {
                    BookDTO bookDTO = new BookDTO();
                    bookDTO.setId(book.getId());
                    bookDTO.setTitle(book.getTitle());
                    bookDTO.setAuthor(book.getAuthor());
                    bookDTO.setPublishedDate(book.getPublishedDate());
                    return bookDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteBookById(int id) {
        if (bookRepo.existsById(id)) {
            try {
                bookRepo.deleteById(id);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    @Override
    public BookDTO updateBook(BookDTO bookDTO , int id) {

        Optional<Book> bookOptional = bookRepo.findById(id);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();

            book.setTitle(bookDTO.getTitle());
            book.setAuthor(bookDTO.getAuthor());
            book.setPublishedDate(bookDTO.getPublishedDate());

            Book updatedBook = bookRepo.save(book);

            BookDTO updatedBookDTO = new BookDTO();
            updatedBookDTO.setId(updatedBook.getId());
            updatedBookDTO.setTitle(updatedBook.getTitle());
            updatedBookDTO.setAuthor(updatedBook.getAuthor());
            updatedBookDTO.setPublishedDate(updatedBook.getPublishedDate());

            return updatedBookDTO;
        }

        return null;
    }
}
