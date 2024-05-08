package com.example.techcare.Controller;
import com.example.techcare.Model.Category;
import com.example.techcare.Model.OrderItem;
import com.example.techcare.Model.Product;
import com.example.techcare.Service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

//6- sara
    @Test
    public void testGetProductById() {
        int productId = 1;

        Category category = new Category();
        OrderItem orderItem = new OrderItem();
        Product expectedProduct = new Product(1, "laptop", "description", 100.00, 0.00, 0, "image", 1, null, category);
        expectedProduct.setId(productId);

        when(productService.getProductById(productId)).thenReturn(expectedProduct);

        ResponseEntity<Product> responseEntity = productController.getProductById(productId);
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(expectedProduct, responseEntity.getBody());
    }
}