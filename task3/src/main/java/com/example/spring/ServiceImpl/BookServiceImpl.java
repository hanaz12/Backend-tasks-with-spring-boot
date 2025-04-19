package com.example.spring.ServiceImpl;

import com.example.spring.DTO.BookDTO;
import com.example.spring.Exceptions.BookNotFoundException;
import com.example.spring.Mapper.BookMapper;
import com.example.spring.Model.Book;
import com.example.spring.Repository.BookRepository;
import com.example.spring.Service.BookService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    @Transactional
    public BookDTO AddBook(BookDTO bookDTO) {
        Book book = bookMapper.toEntity(bookDTO);
        Book saved = bookRepository.save(book);
        return bookMapper.toDTO(saved);
    }

    @Override
    public List<BookDTO> getBooks() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BookDTO GetBookById(int id) {
        return bookRepository.findById(id).map(bookMapper::toDTO).orElseThrow(() -> new BookNotFoundException(id));
    }

    @Override
    @Transactional
    public BookDTO UpdateBook(BookDTO bookDTO) {
        Book book=bookRepository.findById(bookDTO.getId()).orElseThrow(() -> new BookNotFoundException(bookDTO.getId()));
        bookMapper.updateBookFromDTO(bookDTO,book);
        Book saved=bookRepository.save(book);
        return bookMapper.toDTO(saved);
    }

    @Override
    @Transactional
    public void DeleteBook(int id) {
if (!bookRepository.existsById(id)) {
    throw new BookNotFoundException(id);
}
bookRepository.deleteById(id);
    }
}
