package com.example.spring.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

    private int id;

    @NonNull
    @NotBlank
    @Size(min = 2, max = 200 , message = "minimum title size is 2 and max is 200")
    private String title;

    @NonNull
    @NotBlank
    private String author;

    @PastOrPresent(message = "Date can't be in the future")
    private LocalDate publisedhDate;
}
