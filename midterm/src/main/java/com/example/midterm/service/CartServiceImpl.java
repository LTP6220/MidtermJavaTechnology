package com.example.midterm.service;

import com.example.midterm.model.Cart;
import com.example.midterm.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;

    @Override
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    @Override
    public Cart getCartById(int id) {
        return cartRepository.findById(id).get();
    }

    @Override
    public void UpdateCart(Cart cart) {
        cartRepository.save(cart);
    }

    @Override
    public void deleteCartById(int id) {
        cartRepository.deleteById(id);
    }

    @Override
    public void deleteCart(Cart cart) {
        cartRepository.delete(cart);
    }

    @Override
    public Optional<Cart> getCartByProductId(int productId) {
        return cartRepository.findByProductId(productId);
    }
}
