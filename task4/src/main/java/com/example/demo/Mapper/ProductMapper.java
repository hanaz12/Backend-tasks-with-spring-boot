package com.example.demo.Mapper;

import com.example.demo.DTO.ProductDTO;
import com.example.demo.Model.Category;
import com.example.demo.Model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
public interface ProductMapper {

    @Mapping(source = "categories", target = "categories", qualifiedByName = "categoriesToCategoryIds")
    ProductDTO toDTO(Product product);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "categories", expression = "java(categoryIdsToCategories(productDTO.getCategories()))")
    Product toEntity(ProductDTO productDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "categories", expression = "java(categoryIdsToCategories(dto.getCategories()))")
    void updateProductFromDTO(ProductDTO dto, @MappingTarget Product product);

    @Named("categoriesToCategoryIds")
    default List<Integer> categoriesToCategoryIds(List<Category> categories) {
        return categories.stream()
                .map(Category::getId)
                .collect(Collectors.toList());
    }

    default List<Category> categoryIdsToCategories(List<Integer> categoryIds) {
        return categoryIds.stream()
                .map(id -> {
                    Category category = new Category();
                    category.setId(id);
                    return category;
                })
                .collect(Collectors.toList());
    }
}