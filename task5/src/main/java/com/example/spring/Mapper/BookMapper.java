package com.example.spring.Mapper;


import com.example.spring.DTO.BookDTO;
import com.example.spring.Model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookDTO toDTO(Book book);

    Book toEntity(BookDTO bookDTO);

    void updateBookFromDTO(BookDTO bookDTO, @MappingTarget Book book);
}
