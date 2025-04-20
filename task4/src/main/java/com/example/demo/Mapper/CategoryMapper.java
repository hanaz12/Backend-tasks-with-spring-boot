package com.example.demo.Mapper;

import com.example.demo.DTO.CategoryDTO;
import com.example.demo.Model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
public interface CategoryMapper {

    @Mapping(source = "parentCategory.id", target = "parentCategoryId")
    CategoryDTO toDTO(Category category);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "subCategories", ignore = true)
    @Mapping(target = "parentCategory", expression = "java(categoryDTO.getParentCategoryId() == null ? null : createParentCategory(categoryDTO.getParentCategoryId()))")
    Category toEntity(CategoryDTO categoryDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "subCategories", ignore = true)
    @Mapping(target = "parentCategory", expression = "java(dto.getParentCategoryId() == null ? null : createParentCategory(dto.getParentCategoryId()))")
    void updateCategoryFromDTO(CategoryDTO dto, @MappingTarget Category category);


    default Category createParentCategory(Integer parentCategoryId) {
        Category parentCategory = new Category();
        parentCategory.setId(parentCategoryId);
        return parentCategory;
    }
}