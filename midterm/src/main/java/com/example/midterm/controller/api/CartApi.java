package com.example.midterm.controller.api;

import com.example.midterm.model.Cart;
import com.example.midterm.model.Product;
import com.example.midterm.service.CartServiceImpl;
import com.example.midterm.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class CartApi {
    @Autowired
    private CartServiceImpl cartService;

    @Autowired
    private ProductServiceImpl productService;

    @GetMapping("/api/cart")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> viewCart(Model model) {
        List<Cart> carts = cartService.getAllCarts();
        Map<Integer, Product> productMap = getProductMap(carts);
        double total = calculateTotal(carts, productMap);

        model.addAttribute("carts", carts);
        model.addAttribute("products", productMap);
        model.addAttribute("total", total);

        // Create a response map
        Map<String, Object> responseMap = Map.of(
                "carts", carts,
                "products", productMap,
                "total", total
        );

        // You can customize the HttpStatus based on your requirements
        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }

    private Map<Integer, Product> getProductMap(List<Cart> carts) {
        Map<Integer, Product> productMap = new HashMap<>();
        for (Cart cart : carts) {
            int productId = cart.getProductId();
            productMap.computeIfAbsent(productId, k -> {
                return productService.getProductById(k);
            });
        }
        return productMap;
    }

    private double calculateTotal(List<Cart> carts, Map<Integer, Product> productMap) {
        return carts.stream()
                .mapToDouble(cart -> cart.getQuantity() * productMap.get(cart.getProductId()).getPrice())
                .sum();
    }
}
