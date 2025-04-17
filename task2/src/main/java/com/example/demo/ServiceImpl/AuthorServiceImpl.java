package com.example.demo.ServiceImpl;

import com.example.demo.DTO.AuthorDTO;
import com.example.demo.Exceptions.AuthorNotFoundException;
import com.example.demo.Mapper.AuthorMapper;
import com.example.demo.Model.Author;
import com.example.demo.Repository.AuthorRepo;
import com.example.demo.Service.AuthorService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepo authorRepo;
    private final AuthorMapper authorMapper;

    @Override
    public List<AuthorDTO> getAllAuthors() {
        return authorRepo.findAll().stream()
                .map(authorMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public AuthorDTO saveNewAuthor(AuthorDTO authorDTO) {
        Author newAuthor = authorMapper.toEntity(authorDTO);
        Author savedAuthor = authorRepo.save(newAuthor);
        return authorMapper.toDTO(savedAuthor);
    }

    @Override
    public AuthorDTO getAuthorById(int id) {
        Author author = authorRepo.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(id));
        return authorMapper.toDTO(author);
    }

    @Override
    @Transactional
    public AuthorDTO updateAuthor(AuthorDTO authorDTO) {
        Author existingAuthor = authorRepo.findById(authorDTO.getId())
                .orElseThrow(() -> new AuthorNotFoundException(authorDTO.getId()));

        authorMapper.updateAuthorFromDTO(authorDTO, existingAuthor);
        Author savedAuthor = authorRepo.save(existingAuthor);
        return authorMapper.toDTO(savedAuthor);
    }

    @Override
    @Transactional
    public void deleteAuthorById(int id) {
        if (!authorRepo.existsById(id)) {
            throw new AuthorNotFoundException(id);
        }
        authorRepo.deleteById(id);
    }
}