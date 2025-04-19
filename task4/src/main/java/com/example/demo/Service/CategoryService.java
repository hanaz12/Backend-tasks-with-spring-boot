package com.example.demo.Service;

import com.example.demo.DTO.CategoryDTO;
import com.example.demo.Exceptions.CategoryNotFoundException;
import com.example.demo.Exceptions.ParentCategoryNotFoundException;
import com.example.demo.Exceptions.SubcategoriesFoundException;
import jakarta.transaction.Transactional;

import java.util.List;

public interface CategoryService {

    @Transactional
    CategoryDTO AddCategory(CategoryDTO categoryDTO) throws ParentCategoryNotFoundException;

    List<CategoryDTO> GetAllCategories();
    CategoryDTO GetCategoryById(int id) throws CategoryNotFoundException;
    @Transactional
    CategoryDTO UpdateCategory(CategoryDTO categoryDTO , int id) throws CategoryNotFoundException, ParentCategoryNotFoundException;
    @Transactional
    void DeleteCategory(int id) throws SubcategoriesFoundException, CategoryNotFoundException;
}
