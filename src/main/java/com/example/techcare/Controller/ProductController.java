package com.example.techcare.Controller;

import com.example.techcare.Api.ApiResponse;
import com.example.techcare.Model.Product;
import com.example.techcare.Model.User;
import com.example.techcare.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    // 20- Sara: Get all products
    @GetMapping("/get-all")
    public ResponseEntity<List<Product>> getAllProduct(){
        return ResponseEntity.status(200).body(productService.getAllProduct());
    }

    // 21- Sara: Admin add a product
    @PostMapping("/add-product/{catId}")
    public ResponseEntity addProduct(@PathVariable Integer catId, @RequestBody @Valid Product product){
        productService.addProduct(catId,product);
        return ResponseEntity.status(200).body(new ApiResponse("product added successfully"));
    }

    // 22- Sara: Admin update a product
    @PutMapping("/update-product/{productId}/{catId}")
    public ResponseEntity updateProduct(@PathVariable Integer productId, @PathVariable Integer catId, @RequestBody @Valid Product product){
        productService.updateProduct(productId, catId,product);
        return ResponseEntity.status(200).body(new ApiResponse("product updated successfully"));
    }

    // 23- Sara: Admin delete a product
    @DeleteMapping("/delete-product/{productId}")
    public ResponseEntity deleteProduct(@PathVariable Integer productId){
        productService.deleteProduct(productId);
        return ResponseEntity.status(200).body(new ApiResponse("product deleted successfully"));
    }

    // 24- Sara: Get product by id
    @GetMapping("/get-product/{productId}")
    public ResponseEntity getProductById(@PathVariable Integer productId){
        return ResponseEntity.status(200).body(productService.getProductById(productId));
    }

    // 25- Deema: Customer rate purchased product
    @PutMapping("/rate-product/{productId}/{rating}")
    public ResponseEntity rateProduct(@AuthenticationPrincipal User user, @PathVariable Integer productId, @PathVariable Double rating){
        productService.rateProduct(user.getId(), productId, rating);
        return ResponseEntity.status(200).body(new ApiResponse("Product rated successfully"));
    }

    // 26- Sara: Get products by category
    @GetMapping("/get-product-category/{category}")
    public ResponseEntity getProductByCategory(@PathVariable String category){
        return ResponseEntity.ok().body(productService.getProductByCategory(category));
    }

    // 27- Sara: Get products by price range
    @GetMapping("/filter-price/{minPrice}/{maxPrice}")
    public ResponseEntity filterProductPrice(@PathVariable Double minPrice,@PathVariable Double maxPrice){
        return ResponseEntity.ok().body(productService.filterPrice(minPrice,maxPrice));
    }

    // 28- Deema: Get top rated products
    @GetMapping("/top-rated")
    public ResponseEntity getTopRatedProducts(){
        return ResponseEntity.ok().body(productService.getTopRatedProducts());
    }

}
