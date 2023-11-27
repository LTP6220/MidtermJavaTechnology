package com.example.midterm.service;

import com.example.midterm.model.Order;
import com.example.midterm.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order getOrderById(int id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found with id: " + id));

    }

    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrder(int id, Order order) {
        Order existingOrder = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
        existingOrder.setDelivery_address(order.getDelivery_address());
        existingOrder.setOrder_status(order.getOrder_status());
        return orderRepository.save(existingOrder);
    }

    @Override
    public void deleteOrder(int id) {
        orderRepository.deleteById(id);
    }
}
