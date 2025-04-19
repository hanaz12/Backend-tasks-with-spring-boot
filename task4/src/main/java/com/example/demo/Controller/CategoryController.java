package com.example.demo.Controller;

import com.example.demo.DTO.CategoryDTO;
import com.example.demo.Exceptions.SubcategoriesFoundException;
import com.example.demo.Exceptions.SuccessResponse;
import com.example.demo.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("categories")
@AllArgsConstructor
public class CategoryController {
    private CategoryService categoryService;
    @GetMapping
    public ResponseEntity<SuccessResponse> getCategories() {
        List<CategoryDTO> categories=categoryService.GetAllCategories();
        SuccessResponse response=new SuccessResponse("Categories retreived successfully " , HttpStatus.OK.value(),categories);
        return ResponseEntity.ok(response);
    }
    @PostMapping
    public ResponseEntity<SuccessResponse> AddCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
       CategoryDTO category=categoryService.AddCategory(categoryDTO);
        SuccessResponse response=new SuccessResponse("Category added successfully " , HttpStatus.OK.value(),category);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse> getCategoryById(@PathVariable("id") int id) {
        CategoryDTO CategoryDTO = categoryService.GetCategoryById(id);
        SuccessResponse response = new SuccessResponse("Category retrieved successfully", HttpStatus.OK.value(), CategoryDTO);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponse> updateCategory(@PathVariable("id") int id, @Valid @RequestBody CategoryDTO CategoryDTO) {
        CategoryDTO updatedCategoryDTO = categoryService.UpdateCategory(CategoryDTO , id);
        SuccessResponse response = new SuccessResponse("Category updated successfully", HttpStatus.OK.value(), updatedCategoryDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse> deleteCategory(@PathVariable("id") int id) throws SubcategoriesFoundException {
        categoryService.DeleteCategory(id);
        SuccessResponse response = new SuccessResponse("Category deleted successfully", HttpStatus.OK.value(), null);
        return ResponseEntity.ok(response);
    }

}
