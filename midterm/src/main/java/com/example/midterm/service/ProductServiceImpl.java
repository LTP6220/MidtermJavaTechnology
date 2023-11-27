package com.example.midterm.service;

import com.example.midterm.model.Product;
import com.example.midterm.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ProductServiceImpl implements ProductService {


    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(int id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(int id, Product product) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();
            existingProduct.setName(product.getName());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setCategory(product.getCategory());
            existingProduct.setBrand(product.getBrand());
            existingProduct.setColor(product.getColor());
            existingProduct.setPrice(product.getPrice());
            return productRepository.save(existingProduct);
        } else {
            return null;
        }
    }

    @Override
    public void deleteProductById(int id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> getProductsByNameAndCategory(String name, String category) {
        List<Product> productList = this.getAllProducts();
        if (category != null && !category.isEmpty() && !category.equalsIgnoreCase("All")) {
            productList = productList.stream()
                    .filter(p -> p.getCategory().equalsIgnoreCase(category))
                    .collect(Collectors.toList());
        }
        if (name != null && !name.isEmpty()) {
            productList = productList.stream()
                    .filter(p -> p.getName().toLowerCase().contains(name.toLowerCase()))
                    .collect(Collectors.toList());
        }
        return productList;
    }
}
