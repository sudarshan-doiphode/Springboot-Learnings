package com.darsh.service;

import com.darsh.model.Product;
import com.darsh.model.Review;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ProductService {
    private List<Product> products;
    private List<Review> reviews;


    public ProductService() {
        products = Arrays.asList(
                new Product(1, "S22 Ultra"),
                new Product(2, "Samsung Galaxy"),
                new Product(3, "GT 20 pro"),
                new Product(4, "Apple 23"),
                new Product(5, "Poco 22")
        );

        reviews = Arrays.asList(
                new Review(1, "Great product!"),
                new Review(2, "Not bad."),
                new Review(3, "Excellent value."),
                new Review(1, "Could be better."),
                new Review(2, "Worth the price."),
                new Review(5, "Worth the price."),
                new Review(4, "Worth the price.")
        );
    }

    public List<Product> getProducts() {
        if (products.isEmpty()) {
            throw new RuntimeException("Exception");
        } else return products;
    }

    public Product getProductById(int id) {
        return products.stream()
                .filter(product -> product.getProductId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Exception"));
    }

    public List<Review> getReviewsByProductId(int productId) {
        return reviews.stream()
                .filter(review -> review.getProductId() == productId)
                .collect(Collectors.toList());
    }

}
