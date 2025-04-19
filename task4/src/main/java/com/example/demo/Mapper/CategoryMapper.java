package com.example.demo.Mapper;

import com.example.demo.DTO.CategoryDTO;
import com.example.demo.Model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper (componentModel = "spring")
public interface CategoryMapper {

    Category toEntity(CategoryDTO dto);
    CategoryDTO toDto(Category entity);
    void updateCategoryFromDTO(CategoryDTO dto , @MappingTarget Category category);
}
