package com.example.demo.ServiceImpl;

import com.example.demo.DTO.CategoryDTO;
import com.example.demo.Exceptions.CategoryNotFoundException;
import com.example.demo.Exceptions.ParentCategoryNotFoundException;
import com.example.demo.Exceptions.SubcategoriesFoundException;
import com.example.demo.Mapper.CategoryMapper;
import com.example.demo.Model.Category;
import com.example.demo.Repository.CategoryRepository;
import com.example.demo.Service.CategoryService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;


    @Override
    @Transactional
    public CategoryDTO AddCategory(CategoryDTO categoryDTO) {
    Category categoryParent=categoryRepository
            .findById(categoryDTO.getParentCategoryId())
            .orElseThrow(()-> new ParentCategoryNotFoundException(categoryDTO.getParentCategoryId()));
     Category category=categoryMapper.toEntity(categoryDTO);
     return categoryMapper.toDto(categoryRepository.save(category));


    }

    @Override
    public List<CategoryDTO> GetAllCategories() {
        return categoryRepository
                .findAll()
                .stream()
                .map(categoryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO GetCategoryById(int id) {
        Category category=categoryRepository.findById(id).orElseThrow(()-> new CategoryNotFoundException(id));
        return categoryMapper.toDto(category);
    }

    @Override
    public CategoryDTO UpdateCategory(CategoryDTO categoryDTO , int id) {
        Category categoryParent=categoryRepository
                .findById(categoryDTO.getParentCategoryId())
                .orElseThrow(()-> new ParentCategoryNotFoundException(categoryDTO.getParentCategoryId()));

        Category category=categoryRepository
                .findById(id)
                .orElseThrow(()-> new CategoryNotFoundException(categoryDTO.getParentCategoryId()));

        categoryMapper.updateCategoryFromDTO(categoryDTO,category);
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    @Transactional
    public void DeleteCategory(int id) throws SubcategoriesFoundException {
        Category category=
        categoryRepository
                .findById(id).orElseThrow(()-> new CategoryNotFoundException(id));
        if (!category.getSubCategories() .isEmpty()) {
            throw new SubcategoriesFoundException("there are sub categories under this category which can't be removed");
        }
        categoryRepository.delete(category);
    }
}
