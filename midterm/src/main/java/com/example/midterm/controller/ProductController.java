package com.example.midterm.controller;

import com.example.midterm.model.Product;
import com.example.midterm.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @GetMapping("/product-detail")
    public String getProductDetail() {
        return "product-detail";
    }

    @GetMapping
    public String getAllProducts(Model model, @RequestParam(name = "category", required = false) String category, @RequestParam(name = "name", required = false) String name) {
        List<Product> productList;
        if (category == null || category.equalsIgnoreCase("All") && name == null) {
            productList = productService.getAllProducts();
        } else {
            productList = productService.getProductsByNameAndCategory(name, category);
        }
        model.addAttribute("products", productList);
        return "products";
    }

//    @GetMapping
//    public List<Product> getAllProducts() {
//        return productService.getAllProducts();
//    }

    @GetMapping("/{id}")
    public String getProductDetails(@PathVariable("id") int productId, Model model) {
        Product product = productService.getProductById(productId);
        if (product == null) {
            return "products";
        }
        model.addAttribute("product", product);
        return "product-detail";
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable int id) {
        productService.deleteProductById(id);
    }
}
