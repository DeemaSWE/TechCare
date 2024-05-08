package com.example.techcare.Controller;

import com.example.techcare.Api.ApiResponse;
import com.example.techcare.Model.Category;
import com.example.techcare.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    // 2- Deema: Get all categories
    @GetMapping("/get-all")
    public ResponseEntity getAllCategory(){
        return ResponseEntity.status(200).body(categoryService.getAllCategories());
    }

    // 3- Deema: Admin add a category
    @PostMapping("/add")
    public ResponseEntity addCategory(@RequestBody @Valid Category category){
        categoryService.addCategory(category);
        return ResponseEntity.status(200).body(new ApiResponse("Category added successfully"));
    }

    // 4- Deema: Admin update a category
    @PutMapping("/update/{categoryId}")
    public ResponseEntity updateCategory(@PathVariable Integer categoryId, @RequestBody @Valid Category updatedCategory){
        categoryService.updateCategory(categoryId, updatedCategory);
        return ResponseEntity.status(200).body(new ApiResponse("Category updated successfully"));
    }

    // 5- Deema: Admin delete a category
    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity deleteCategory(@PathVariable Integer categoryId){
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.status(200).body(new ApiResponse("Category deleted successfully"));
    }
}
