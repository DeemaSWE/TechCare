package com.example.techcare.Service;

import com.example.techcare.Model.Category;
import com.example.techcare.Repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    private Category category1;
    private Category category2;

    @BeforeEach
    public void setup() {
        category1 = new Category(1, "Laptops", null, null);

        category2 = new Category(2, "Smartphones", null, null);

    }

    // 1- Deema
    @Test
    public void testGetAllCategories() {
        when(categoryRepository.findAll()).thenReturn(Arrays.asList(category1, category2));

        List<Category> categories = categoryService.getAllCategories();

        assertEquals(2, categories.size());
        verify(categoryRepository, times(1)).findAll();
    }

    // 2- Deema
    @Test
    public void testAddCategory() {
        categoryService.addCategory(category1);

        verify(categoryRepository, times(1)).save(category1);
    }

    // 3- Deema
    @Test
    public void testUpdateCategory() {
        when(categoryRepository.findCategoryById(category1.getId())).thenReturn(category1);

        Category updatedCategory = new Category();
        updatedCategory.setName("Updated Category");
        categoryService.updateCategory(category1.getId(), updatedCategory);

        assertEquals(updatedCategory.getName(), category1.getName());
        verify(categoryRepository, times(1)).save(category1);
    }

    // 4- Deema
    @Test
    public void testDeleteCategory() {
        when(categoryRepository.findCategoryById(category1.getId())).thenReturn(category1);

        categoryService.deleteCategory(category1.getId());

        verify(categoryRepository, times(1)).delete(category1);
    }
}