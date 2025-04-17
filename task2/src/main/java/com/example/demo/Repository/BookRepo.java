package com.example.demo.Repository;

import com.example.demo.Model.Author;
import com.example.demo.Model.Book;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepo extends JpaRepository<Book, Integer> {

    boolean existsByTitle(String title);
    List<Book> findByTitleContainingIgnoreCase(String title);
    List<Book> findByAuthor(Author author);
    Optional<Book> findByIdAndAuthorId(int id, int authorId);
}

