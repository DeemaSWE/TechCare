package com.example.techcare.Repository;

import com.example.techcare.Model.Category;
import com.example.techcare.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Product,Integer> {
    Product findProductById(Integer id);
    List<Product> findProductByCategory(Category category);

    List<Product> findProductsByPriceBetween(Double min, Double max);

    List<Product> findTop10ByOrderByAvgRatingDesc();
 }
