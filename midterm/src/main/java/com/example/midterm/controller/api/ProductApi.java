package com.example.midterm.controller.api;

import com.example.midterm.model.Product;
import com.example.midterm.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ProductApi {
    @Autowired
    private ProductServiceImpl productService;

    @GetMapping("/api/products") // You can customize the endpoint
    @ResponseBody
    public ResponseEntity<List<Product>> getAllProductsJson(
            @RequestParam(name = "category", required = false) String category,
            @RequestParam(name = "name", required = false) String name) {
        List<Product> productList;
        if (category == null || category.equalsIgnoreCase("All") && name == null) {
            productList = productService.getAllProducts();
        } else {
            productList = productService.getProductsByNameAndCategory(name, category);
        }

        // You can add additional headers or status codes if needed
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("/api/products/{id}") // You can customize the endpoint
    @ResponseBody
    public ResponseEntity<Product> getProductDetailsJson(@PathVariable("id") int productId) {
        Product product = productService.getProductById(productId);
        if (product == null) {
            // You can customize the status code and add additional headers if needed
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // You can add additional headers or status codes if needed
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
