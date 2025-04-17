package com.example.demo.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    private int id;
    @NotBlank(message = "Title cannot be blank")
    @Size(min = 2, max = 20, message = "Title must be between 2 and 20 characters")
    private String title;

   // @Positive(message = "Author ID must be a positive number")
    private int authorId;

    @PastOrPresent(message = "Publication date cannot be in the future")
    private LocalDate publishedDate;
}
