package com.example.demo.Controller;

import com.example.demo.DTO.AuthorDTO;
import com.example.demo.DTO.BookDTO;
import com.example.demo.Exceptions.AuthorNotFoundException;
import com.example.demo.Exceptions.BookNotFoundException;
import com.example.demo.Service.AuthorService;
import com.example.demo.Service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;
    @Autowired
    private BookService bookService;

    @GetMapping({"", "/"})
    public ResponseEntity<List<AuthorDTO>> getAuthors() {
        List<AuthorDTO> authors = authorService.getAllAuthors();
        return ResponseEntity.ok(authors);
    }

    @PostMapping({"", "/"})
    public ResponseEntity<String> addAuthor(@Valid @RequestBody AuthorDTO authorDTO) {
        boolean saved = authorService.saveNewAuthor(authorDTO);
        if (saved) {
            return ResponseEntity.ok("Saved Successfully");
        } else {
            return ResponseEntity.ok("Saving Failed");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable("id") int id) {
        AuthorDTO authorDTO = authorService.getAuthorById(id);
        if (authorDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(authorDTO);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAuthor(@PathVariable("id") int id, @Valid @RequestBody AuthorDTO authorDTO) {
        authorDTO.setId(id);
        AuthorDTO updatedauthorDTO=authorService.updateAuthor(authorDTO);
        if (updatedauthorDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(updatedauthorDTO);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable("id") int id) {
        boolean deletedAuthor=authorService.deleteAuthorById(id);
        if (deletedAuthor) {
            return ResponseEntity.ok("Deleted Successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    @PostMapping("/{id}/books")
    public ResponseEntity<String> addBook(@PathVariable("id") int id, @Valid @RequestBody BookDTO bookDTO) {
        boolean result = bookService.addBook(bookDTO, id);
        if (result) {
            return new ResponseEntity<>("Book added successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to add book", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{id}/books")
    public ResponseEntity<List<BookDTO>> getBooks(@PathVariable("id") int id) {
        try {
            List<BookDTO> books = bookService.getBooksByAuthor(id);
            if (books.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(books, HttpStatus.OK);
        } catch (AuthorNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{authorId}/books/{bookId}")
    public ResponseEntity<BookDTO> getBookByIdAndAuthorId(
            @PathVariable("authorId") int authorId,
            @PathVariable("bookId") int bookId) {

        try {
            BookDTO book = bookService.getBookByIdAndAuthorId(bookId, authorId);
            return new ResponseEntity<>(book, HttpStatus.OK);
        } catch (AuthorNotFoundException | BookNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{authorId}/books/{bookId}")
    public ResponseEntity<BookDTO> updateBookByAuthorId(
            @PathVariable("authorId") int authorId,
            @PathVariable("bookId") int bookId,
            @Valid @RequestBody BookDTO bookDTO) {

        try {
            BookDTO updatedBook = bookService.updateBookByAuthorId(bookDTO, bookId, authorId);
            return new ResponseEntity<>(updatedBook, HttpStatus.OK);
        } catch (AuthorNotFoundException | BookNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

 
    @DeleteMapping("/{authorId}/books/{bookId}")
    public ResponseEntity<?> deleteBookByAuthorId(
            @PathVariable("authorId") int authorId,
            @PathVariable("bookId") int bookId) {

        try {
            boolean result = bookService.deleteBookByAuthorId(bookId, authorId);
            if (result) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (AuthorNotFoundException | BookNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
