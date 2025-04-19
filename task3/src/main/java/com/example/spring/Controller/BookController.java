package com.example.spring.Controller;


import com.example.spring.DTO.BookDTO;
import com.example.spring.Exceptions.SuccessResponse;
import com.example.spring.Service.BookService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@AllArgsConstructor
public class BookController {
private final BookService bookService;
    @GetMapping({"", "/"})
    public ResponseEntity<SuccessResponse> getBooks() {
        List<BookDTO> Books = bookService.getBooks();
        SuccessResponse response = new SuccessResponse("Books retrieved successfully", HttpStatus.OK.value(), Books);
        return ResponseEntity.ok(response);
    }

    @PostMapping({"", "/"})
    public ResponseEntity<SuccessResponse> addBook(@Valid @RequestBody BookDTO BookDTO) {
        BookDTO savedBook = bookService.AddBook(BookDTO);
        SuccessResponse response = new SuccessResponse("Book saved successfully", HttpStatus.CREATED.value(), savedBook);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse> getBookById(@PathVariable("id") int id) {
        BookDTO BookDTO = bookService.GetBookById(id);
        SuccessResponse response = new SuccessResponse("Book retrieved successfully", HttpStatus.OK.value(), BookDTO);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponse> updateBook(@PathVariable("id") int id, @Valid @RequestBody BookDTO BookDTO) {
        BookDTO.setId(id);
        BookDTO updatedBookDTO = bookService.UpdateBook(BookDTO);
        SuccessResponse response = new SuccessResponse("Book updated successfully", HttpStatus.OK.value(), updatedBookDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse> deleteBook(@PathVariable("id") int id) {
        bookService.DeleteBook(id);
        SuccessResponse response = new SuccessResponse("Book deleted successfully", HttpStatus.OK.value(), null);
        return ResponseEntity.ok(response);
    }
}
