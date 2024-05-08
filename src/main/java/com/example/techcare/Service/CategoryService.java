package com.example.techcare.Service;

import com.example.techcare.Api.ApiException;
import com.example.techcare.Model.Category;
import com.example.techcare.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    // 2- Deema: Get all categories
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // 3- Deema: Admin add a category
    public void addCategory(Category category) {
        categoryRepository.save(category);
    }

    // 4- Deema: Admin update a category
    public void updateCategory(Integer categoryId, Category updatedCategory) {
        Category category = categoryRepository.findCategoryById(categoryId);

        if(category == null)
            throw new ApiException("Category not found");

        category.setName(updatedCategory.getName());

        categoryRepository.save(category);
    }

    // 5- Deema: Admin delete a category
    public void deleteCategory(Integer categoryId) {
        Category category = categoryRepository.findCategoryById(categoryId);

        if(category == null)
            throw new ApiException("Category not found");

        categoryRepository.delete(category);
    }
}
