package com.ashok.graphql.controller;

import com.ashok.graphql.dto.ProductSearchRequest;
import com.ashok.graphql.entity.Product;
import com.ashok.graphql.serviceimpl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    // Endpoint to fetch all products
    @QueryMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // Endpoint to fetch a product by ID
    @QueryMapping
    public Product getProductById(@Argument Long id) {
        Optional<Product> product = productService.getProductById(id);
        return product.orElse(null);
    }

    // Endpoint to search products based on criteria in request body
    @MutationMapping
    public List<Product> searchProducts(@Argument ProductSearchRequest request) {
        return productService.searchProducts(request);
    }

    // Endpoint to create a new product
    @MutationMapping
    public Product createProduct(@Argument Product product) {
        return productService.createProduct(product);
    }

    // Endpoint to update an existing product
    @MutationMapping
    public Product updateProduct(@Argument Long id, @RequestBody Product productDetails) {
        return productService.updateProduct(id, productDetails);
    }

    // Endpoint to delete a product by ID
    @MutationMapping
    public void deleteProduct(@Argument Long id) {
        productService.deleteProduct(id);
    }
}
