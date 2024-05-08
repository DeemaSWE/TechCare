package com.example.techcare.Service;

import com.example.techcare.Api.ApiException;
import com.example.techcare.Model.*;
import com.example.techcare.Repository.CategoryRepository;
import com.example.techcare.Repository.CustomerRepository;
import com.example.techcare.Repository.OrderItemRepository;
import com.example.techcare.Repository.ProductsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductsRepository productsRepository;
    private final CustomerRepository customersRepository;
    private final OrderItemRepository orderItemRepository;
    private final CategoryRepository categoryRepository;

    // 20- Sara: Get all products
    public List<Product> getAllProduct(){
        return productsRepository.findAll();
    }

    // 21- Sara: Admin add a product
    public void addProduct(Integer catId,Product product){//sara //asgin product to category//String categoryName,
        Category category=categoryRepository.findCategoryById(catId);
        if (category==null){
            throw new ApiException("not found category");
        }
        product.setAvgRating(0.0);
        product.setRatingCount(0);
        product.setCategory(category);
        productsRepository.save(product);
    }

    // 22- Sara: Admin update a product
    public void updateProduct(Integer productId,Integer catId,Product newProduct){
        Product product1=productsRepository.findProductById(productId);
        Category category=categoryRepository.findCategoryById(catId);
        if (category==null){
            throw new ApiException("not found category");
        }
        if (product1==null){
            throw new ApiException("product not found");
        }
        if (product1.getId().equals(newProduct.getId())){
        product1.setName(newProduct.getName());
        product1.setDescription(newProduct.getDescription());
        product1.setImageUrl(newProduct.getImageUrl());
        product1.setCategory(category);
        productsRepository.save(product1);}
        else throw new ApiException("id product not matches ");
    }

    // 23- Sara: Admin delete a product
    public void deleteProduct(Integer productId){
        Product product=productsRepository.findProductById(productId);
        if (product==null){
            throw new ApiException("Product not found");
        }
        productsRepository.delete(product);
    }

    // 24- Sara: Get product by id
    public Product getProductById(Integer productId){
        Product product=productsRepository.findProductById(productId);
        if (product==null){
            throw new ApiException("Product not found");
        }
        return product;
    }

    // 25- Deema: Customer rate purchased product
    public void rateProduct(Integer customerId, Integer productId, Double rating){
        Customer customer = customersRepository.findCustomerById(customerId);
        Product product = productsRepository.findProductById(productId);

        if(product == null)
            throw new ApiException("Product not found");

        Set<Orders> customerOrders = customer.getOrders();

        // check if customer has purchased the product
        boolean hasPurchased = false;

        for(Orders order : customerOrders){
            OrderItem orderItem = orderItemRepository.findOrderItemByProductAndOrder(product, order);

            if (orderItem != null){
                hasPurchased = true;
                break;
            }
        }

        if(!hasPurchased)
            throw new ApiException("You have not purchased this product to rate it");

        if(rating < 1 || rating > 5)
            throw new ApiException("Rating must be between 1 and 5");

        double averageRating = product.getAvgRating();
        int ratingCount = product.getRatingCount();

        double newAverageRating = (averageRating * ratingCount + rating) / (ratingCount + 1);
        product.setAvgRating(newAverageRating);
        product.setRatingCount(ratingCount + 1);

        productsRepository.save(product);
    }


    // 26- Sara: Get products by category
    public List<Product> getProductByCategory(String cat){
        Category category=categoryRepository.findCategoryByName(cat);
        List<Product> productList=productsRepository.findProductByCategory(category);
        if (category==null){
            throw new ApiException("not found category");
        }
        if (productList.isEmpty()){
            throw new ApiException("not found result");
        }
        return productList;
    }

    // 27- Sara: Get products by price range
    public List<Product> filterPrice(Double minPrice, Double maxPrice) {
        List<Product> productList=productsRepository.findProductsByPriceBetween(minPrice,maxPrice);
        if (productList.isEmpty()){
            throw new ApiException("no result found");
        }
        productList.sort(Comparator.comparingDouble(Product::getPrice));
        return productList;
    }

    // 28- Deema: Get top rated products
    public List<Product> getTopRatedProducts(){
        return productsRepository.findTop10ByOrderByAvgRatingDesc();
    }
}
