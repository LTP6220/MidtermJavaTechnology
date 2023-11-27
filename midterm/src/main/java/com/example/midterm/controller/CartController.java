package com.example.midterm.controller;

import com.example.midterm.model.Cart;
import com.example.midterm.model.Order;
import com.example.midterm.model.Product;
import com.example.midterm.service.CartServiceImpl;
import com.example.midterm.service.OrderServiceImpl;
import com.example.midterm.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartServiceImpl cartService;

    @Autowired
    private OrderServiceImpl ordersService;

    @Autowired
    private ProductServiceImpl productService;

    @GetMapping("/cart")
    public String cart() {
        return "cart";
    }

    @GetMapping("/checkout1")
    public String checkout1() {
        return "checkout";
    }

    @GetMapping
    public String viewCart(Model model) {
        List<Cart> carts = cartService.getAllCarts();
        Map<Integer, Product> productMap = getProductMap(carts);
        double total = calculateTotal(carts, productMap);

        model.addAttribute("carts", carts);
        model.addAttribute("products", productMap);
        model.addAttribute("total", total);

        return "cart";
    }

    @PostMapping
    public String addToCart(@RequestParam int product_id, @RequestParam int quantity) {
        Product product = productService.getProductById(product_id);

        Cart cart = cartService.getCartByProductId(product_id)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setProductId(product.getId());
                    return newCart;
                });

        cart.setQuantity(cart.getQuantity() + quantity);
        cartService.UpdateCart(cart);

        return "redirect:/cart";
    }

    @PostMapping("/checkout")
    public String placeOrder(@RequestParam int cartId, @RequestParam String deliveryAddress, @RequestParam String phone) {
        Cart cart = cartService.getCartById(cartId);
        double subtotal = calculateSubtotal(cart);
        Order order = createOrder(deliveryAddress, phone, cart, subtotal);
        ordersService.createOrder(order);
        return "checkout-success";
    }

    @GetMapping("/checkout")
    public String checkout(Model model, @RequestParam int cartId) {
        Cart cart = getCartById(cartId);
        Product product = getProductFromCart(cart);
        double subtotal = calculateSubtotal(cart);

        model.addAttribute("cart", cart);
        model.addAttribute("product", product);
        model.addAttribute("subtotal", subtotal);

        return "checkout";
    }

    private Cart getCartById(int cartId) {
        return cartService.getCartById(cartId);
    }

    private Product getProductFromCart(Cart cart) {
        return productService.getProductById(cart.getProductId());
    }

    private double calculateSubtotal(Cart cart) {
        Product product = getProductFromCart(cart);
        return cart.getQuantity() * product.getPrice();
    }

    private Order createOrder(String deliveryAddress, String phone, Cart cart, double subtotal) {
        Order order = new Order();
        order.setDelivery_address(deliveryAddress);
        order.setOrder_status("Not delivered");
        order.setPhone(phone);
        order.setCartId(cart.getId());
        order.setTotal_price(subtotal);
        return order;
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
