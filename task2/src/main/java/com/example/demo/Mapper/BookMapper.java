package com.example.demo.Mapper;


import com.example.demo.DTO.BookDTO;
import com.example.demo.Model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(target = "author", ignore = true)
    Book toEntity(BookDTO bookDTO);

    @Mapping(source = "author.id", target = "authorId")
    BookDTO toDTO(Book book);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", ignore = true)
    void updateBookFromDTO(BookDTO bookDTO, @MappingTarget Book book);
}
