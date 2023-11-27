package com.example.midterm.service;

import com.example.midterm.model.Order;

public interface OrderService {
    Order getOrderById(int id);

    Order createOrder(Order order);

    Order updateOrder(int id, Order order);

    void deleteOrder(int id);
}
