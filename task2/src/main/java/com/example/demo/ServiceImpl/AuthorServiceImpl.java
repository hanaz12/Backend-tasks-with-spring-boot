package com.example.demo.ServiceImpl;

import com.example.demo.DTO.AuthorDTO;
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
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorRepo authorRepo;

    @Autowired
    private AuthorMapper authorMapper;

    @Override
    public List<AuthorDTO> getAllAuthors() {
        return authorRepo.findAll().stream()
                .map(author -> authorMapper.toDTO(author))
                .collect(Collectors.toList());

    }

    @Override
    @Transactional
    public boolean saveNewAuthor(AuthorDTO authorDTO) {
        try{
            Author newAuthor = authorMapper.toEntity(authorDTO);
            authorRepo.save(newAuthor);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public AuthorDTO getAuthorById(int id) {
        Optional<Author> optionalAuthor = authorRepo.findById(id);

        if (optionalAuthor.isPresent()) {
            Author author = optionalAuthor.get();
            return authorMapper.toDTO(author);
        }
        return null;
    }

    @Override
    public AuthorDTO updateAuthor(AuthorDTO authorDTO) {
        Optional<Author> authorOptional = authorRepo.findById(authorDTO.getId());
        if (authorOptional.isPresent()) {
            Author oldAuthor = authorOptional.get();

            // استخدام دالة التحديث من AuthorMapper
            authorMapper.updateAuthorFromDTO(authorDTO, oldAuthor);

            Author savedAuthor = authorRepo.save(oldAuthor);
            return authorMapper.toDTO(savedAuthor);
        }
        return null;
    }

    @Override
    public boolean deleteAuthorById(int id) {
        if (authorRepo.existsById(id)) {
            try {
                authorRepo.deleteById(id);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

}

