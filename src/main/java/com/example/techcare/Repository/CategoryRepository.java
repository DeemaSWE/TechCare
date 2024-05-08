package com.example.techcare.Repository;

import com.example.techcare.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Category findCategoryById(Integer id);
    Category findCategoryByName(String cat);
}
