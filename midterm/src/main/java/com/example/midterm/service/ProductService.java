package com.example.midterm.service;

import com.example.midterm.model.Product;

import java.util.List;

public interface ProductService {
    public List<Product> getAllProducts();

    public Product getProductById(int id);

    public Product addProduct(Product product);

    public Product updateProduct(int id, Product product);

    public void deleteProductById(int id);

    public List<Product> getProductsByNameAndCategory(String name, String category);
}
