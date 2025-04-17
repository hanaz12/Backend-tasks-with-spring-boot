package com.example.demo.Mapper;

import com.example.demo.DTO.AuthorDTO;
import com.example.demo.Model.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {BookMapper.class})
public interface AuthorMapper {

    @Mapping(target = "books", ignore = true)
    Author toEntity(AuthorDTO authorDTO);

    AuthorDTO toDTO(Author author);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "books", ignore = true)
    void updateAuthorFromDTO(AuthorDTO authorDTO, @MappingTarget Author author);
}