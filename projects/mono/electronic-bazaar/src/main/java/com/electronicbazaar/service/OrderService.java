package com.electronicbazaar.service;

import com.electronicbazaar.model.Order;
import com.electronicbazaar.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderService {
    private final OrderRepository repo;

    public OrderService(OrderRepository repo) {
        this.repo = repo;
    }

    public Order create(Order order) {
        order.setOrderDate(LocalDateTime.now());
        return repo.save(order);
    }

    public Order get(Long id) { return repo.findById(id).orElseThrow(); }
}
