package com.crio.buy_buddy_order_service.service;

import java.util.List;

import com.crio.buy_buddy_order_service.dto.OrderRequest;
import com.crio.buy_buddy_order_service.model.Order;

public interface OrderService {
    List<Order> getAllOrders();
    Order getOrderById(Long id);
    Order createOrder(OrderRequest orderRequest);
    void deleteOrder(Long id);
}
