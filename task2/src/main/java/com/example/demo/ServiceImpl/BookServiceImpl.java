package com.example.demo.ServiceImpl;

import com.example.demo.DTO.BookDTO;
import com.example.demo.Exceptions.AuthorNotFoundException;
import com.example.demo.Exceptions.BookNotFoundException;
import com.example.demo.Mapper.BookMapper;
import com.example.demo.Model.Author;
import com.example.demo.Model.Book;
import com.example.demo.Repository.AuthorRepo;
import com.example.demo.Repository.BookRepo;
import com.example.demo.Service.BookService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepo bookRepo;
    private final AuthorRepo authorRepo;
    private final BookMapper bookMapper;

    @Override
    @Transactional
    public BookDTO addBook(BookDTO bookDTO, int authorId) throws AuthorNotFoundException, IllegalArgumentException {
        Author author = authorRepo.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException(authorId));

        Book book = bookMapper.toEntity(bookDTO);
        book.setAuthor(author);

        if (book.getTitle() == null || book.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Book title cannot be empty");
        }

        if (bookRepo.existsByTitle(book.getTitle())) {
            throw new IllegalArgumentException("A book with this title already exists");
        }

        Book savedBook = bookRepo.save(book);
        return bookMapper.toDTO(savedBook);
    }

    @Override
    public List<BookDTO> getBooksByAuthor(int authorId) {

        authorRepo.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException(authorId));


        List<Book> books = bookRepo.findByAuthorId(authorId);

        return books.stream()
                .map(bookMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BookDTO getBookByIdAndAuthorId(int bookId, int authorId) throws BookNotFoundException, AuthorNotFoundException {

        authorRepo.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException(authorId));


        Book book = bookRepo.findByIdAndAuthorId(bookId, authorId)
                .orElseThrow(() -> new BookNotFoundException(bookId, authorId));

        return bookMapper.toDTO(book);
    }

    @Override
    @Transactional
    public BookDTO updateBookByAuthorId(BookDTO bookDTO, int bookId, int authorId) throws BookNotFoundException, AuthorNotFoundException {

        Author author = authorRepo.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException(authorId));


        Book book = bookRepo.findByIdAndAuthorId(bookId, authorId)
                .orElseThrow(() -> new BookNotFoundException(bookId, authorId));


        bookMapper.updateBookFromDTO(bookDTO, book);


        Book savedBook = bookRepo.save(book);
        return bookMapper.toDTO(savedBook);
    }

    @Override
    @Transactional
    public void deleteBookByAuthorId(int bookId, int authorId) throws BookNotFoundException, AuthorNotFoundException {

        authorRepo.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException(authorId));


        Book book = bookRepo.findByIdAndAuthorId(bookId, authorId)
                .orElseThrow(() -> new BookNotFoundException(bookId, authorId));


        bookRepo.delete(book);
    }
}
