package com.example.demo.Controller;

import com.example.demo.DTO.AuthorDTO;
import com.example.demo.DTO.BookDTO;
import com.example.demo.Exceptions.AuthorNotFoundException;
import com.example.demo.Exceptions.BookNotFoundException;
import com.example.demo.Exceptions.SuccessResponse;
import com.example.demo.Service.AuthorService;
import com.example.demo.Service.BookService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;
    private final BookService bookService;

    @GetMapping({"", "/"})
    public ResponseEntity<SuccessResponse> getAuthors() {
        List<AuthorDTO> authors = authorService.getAllAuthors();
        SuccessResponse response = new SuccessResponse("Authors retrieved successfully", HttpStatus.OK.value(), authors);
        return ResponseEntity.ok(response);
    }

    @PostMapping({"", "/"})
    public ResponseEntity<SuccessResponse> addAuthor(@Valid @RequestBody AuthorDTO authorDTO) {
        AuthorDTO savedAuthor = authorService.saveNewAuthor(authorDTO);
        SuccessResponse response = new SuccessResponse("Author saved successfully", HttpStatus.CREATED.value(), savedAuthor);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse> getAuthorById(@PathVariable("id") int id) {
        AuthorDTO authorDTO = authorService.getAuthorById(id);
        SuccessResponse response = new SuccessResponse("Author retrieved successfully", HttpStatus.OK.value(), authorDTO);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponse> updateAuthor(@PathVariable("id") int id, @Valid @RequestBody AuthorDTO authorDTO) {
        authorDTO.setId(id);
        AuthorDTO updatedAuthorDTO = authorService.updateAuthor(authorDTO);
        SuccessResponse response = new SuccessResponse("Author updated successfully", HttpStatus.OK.value(), updatedAuthorDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse> deleteAuthor(@PathVariable("id") int id) {
        authorService.deleteAuthorById(id);
        SuccessResponse response = new SuccessResponse("Author deleted successfully", HttpStatus.OK.value(), null);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/books")
    public ResponseEntity<SuccessResponse> addBook(@PathVariable("id") int id, @Valid @RequestBody BookDTO bookDTO) {
        BookDTO savedBook = bookService.addBook(bookDTO, id);
        SuccessResponse response = new SuccessResponse("Book added successfully", HttpStatus.CREATED.value(), savedBook);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}/books")
    public ResponseEntity<SuccessResponse> getBooks(@PathVariable("id") int id) {
        List<BookDTO> books = bookService.getBooksByAuthor(id);
        String message = books.isEmpty() ? "No books found for author with ID " + id : "Books retrieved successfully";
        SuccessResponse response = new SuccessResponse(message, HttpStatus.OK.value(), books);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{authorId}/books/{bookId}")
    public ResponseEntity<SuccessResponse> getBookByIdAndAuthorId(
            @PathVariable("authorId") int authorId,
            @PathVariable("bookId") int bookId) {
        BookDTO book = bookService.getBookByIdAndAuthorId(bookId, authorId);
        SuccessResponse response = new SuccessResponse("Book retrieved successfully", HttpStatus.OK.value(), book);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{authorId}/books/{bookId}")
    public ResponseEntity<SuccessResponse> updateBookByAuthorId(
            @PathVariable("authorId") int authorId,
            @PathVariable("bookId") int bookId,
            @Valid @RequestBody BookDTO bookDTO) {
        BookDTO updatedBook = bookService.updateBookByAuthorId(bookDTO, bookId, authorId);
        SuccessResponse response = new SuccessResponse("Book updated successfully", HttpStatus.OK.value(), updatedBook);
        return ResponseEntity.ok(response);
    }

<<<<<<< HEAD
=======
 
>>>>>>> 3fe949d06f80355f923d7b5b6513cbcf28b2c526
    @DeleteMapping("/{authorId}/books/{bookId}")
    public ResponseEntity<SuccessResponse> deleteBookByAuthorId(
            @PathVariable("authorId") int authorId,
            @PathVariable("bookId") int bookId) {
        bookService.deleteBookByAuthorId(bookId, authorId);
        SuccessResponse response = new SuccessResponse("Book deleted successfully", HttpStatus.OK.value(), null);
        return ResponseEntity.ok(response);
    }
}
