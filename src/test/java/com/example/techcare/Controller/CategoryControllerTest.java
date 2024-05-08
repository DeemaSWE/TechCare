package com.example.techcare.Controller;

import com.example.techcare.Model.Category;
import com.example.techcare.Service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {

    @MockBean
    private CategoryService categoryService;

    @Autowired
    private MockMvc mockMvc;

    private Category category1;
    private Category category2;


    @InjectMocks
    private CategoryController categoryController;


    @BeforeEach
    public void setup() {
        category1 = new Category(1, "Laptops", null, null);
        category2 = new Category(2, "Smartphones", null, null);
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
    }

    // 1- Deema
    @Test
    @WithMockUser
    public void testGetAllCategories() throws Exception {
        when(categoryService.getAllCategories()).thenReturn(Arrays.asList(category1, category2));

        mockMvc.perform(get("/api/v1/category/get-all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


//2-sara
    @Test
    public void testAddCategory() throws Exception {
        doNothing().when(categoryService).addCategory(any(Category.class));

        mockMvc.perform(post("/api/v1/category/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Test Category\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Category added successfully"));

        verify(categoryService, times(1)).addCategory(any(Category.class));
    }

    //3-sara
    @Test
    public void testUpdateCategory() throws Exception {
        Category updatedCategory = new Category();
        updatedCategory.setName("Updated Category");

        doNothing().when(categoryService).updateCategory(eq(1), any(Category.class));

        mockMvc.perform(put("/api/v1/category/update/{categoryId}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Updated Category\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Category updated successfully"));

        verify(categoryService, times(1)).updateCategory(eq(1), any(Category.class));
    }
    //4-sara
    @Test
    public void testDeleteCategory() throws Exception {
        doNothing().when(categoryService).deleteCategory(1);

        mockMvc.perform(delete("/api/v1/category/delete/{categoryId}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Category deleted successfully"));

        verify(categoryService, times(1)).deleteCategory(1);
    }
}