package com.example.midterm.service;

import com.example.midterm.model.Cart;

import java.util.List;
import java.util.Optional;

public interface CartService {
    public List<Cart> getAllCarts();

    public Cart getCartById(int id);

    public void UpdateCart(Cart cart);

    public void deleteCartById(int id);

    public void deleteCart(Cart cart);

    public Optional<Cart> getCartByProductId(int productId);

}
