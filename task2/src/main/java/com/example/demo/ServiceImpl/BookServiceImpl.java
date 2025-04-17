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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private AuthorRepo authorRepo;
    @Autowired
    private BookMapper bookMapper;

    @Transactional
    @Override
    public boolean addBook(BookDTO bookDTO, int authorId) throws AuthorNotFoundException, IllegalArgumentException {
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
        return savedBook != null && savedBook.getId() > 0;
    }

    @Override
    public List<BookDTO> getBooksByAuthor(int authorId) {
        Optional<Author> optionalAuthor = authorRepo.findById(authorId);

        if (!optionalAuthor.isPresent()) {
            throw new AuthorNotFoundException("Author with ID " + authorId + " not found");
        }

        Author author = optionalAuthor.get();
        List<Book> books = author.getBooks();


        return books.stream()
                .map(book -> bookMapper.toDTO(book))
                .collect(Collectors.toList());


    }

    @Override
    public BookDTO getBookByIdAndAuthorId(int bookId, int authorId) throws BookNotFoundException, AuthorNotFoundException {
        if (!authorRepo.existsById(authorId)) {
            throw new AuthorNotFoundException("Author with ID " + authorId + " not found");
        }

        Book book = bookRepo.findByIdAndAuthorId(bookId, authorId)
                .orElseThrow(() -> new BookNotFoundException("Book with ID " + bookId +
                        " not found for author with ID " + authorId));

        return bookMapper.toDTO(book);
    }

    @Override
    public BookDTO updateBookByAuthorId(BookDTO bookDTO, int bookId, int authorId) throws BookNotFoundException, AuthorNotFoundException {
        Author author = authorRepo.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException("Author with ID " + authorId + " not found"));

        Book book = bookRepo.findByIdAndAuthorId(bookId, authorId)
                .orElseThrow(() -> new BookNotFoundException("Book with ID " + bookId +
                        " not found for author with ID " + authorId));


        bookMapper.updateBookFromDTO(bookDTO, book);
        book.setId(bookId);
        book.setAuthor(author);

        Book savedBook = bookRepo.save(book);
        return bookMapper.toDTO(savedBook);
    }

    @Override
    public boolean deleteBookByAuthorId(int bookId, int authorId) throws BookNotFoundException, AuthorNotFoundException {
        if (!authorRepo.existsById(authorId)) {
            throw new AuthorNotFoundException("Author with ID " + authorId + " not found");
        }

        Book book = bookRepo.findByIdAndAuthorId(bookId, authorId)
                .orElseThrow(() -> new BookNotFoundException("Book with ID " + bookId +
                        " not found for author with ID " + authorId));

        try {
            bookRepo.delete(book);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
