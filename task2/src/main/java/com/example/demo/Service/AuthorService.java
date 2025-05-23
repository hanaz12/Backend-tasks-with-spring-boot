package com.example.demo.Service;

import com.example.demo.DTO.AuthorDTO;

import java.util.List;

public interface AuthorService {
    public List<AuthorDTO> getAllAuthors();
    public AuthorDTO saveNewAuthor(AuthorDTO author);
    public AuthorDTO getAuthorById(int id);
    public AuthorDTO updateAuthor(AuthorDTO author);
    void deleteAuthorById(int id);

}
