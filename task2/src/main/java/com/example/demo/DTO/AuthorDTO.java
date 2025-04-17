package com.example.demo.DTO;

import com.example.demo.Model.Book;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDTO {

    private int id;
    @NotBlank(message ="name can't be null")
    @Size(min = 2, max = 20 , message="Name should be at least 3 chars")

    private String name;

    @NotNull(message = "Birthdate can't be null")
    @Past(message="Birth date Should be in the past")
    private Date birthday;

    private List<BookDTO> books = new ArrayList<>();
}
