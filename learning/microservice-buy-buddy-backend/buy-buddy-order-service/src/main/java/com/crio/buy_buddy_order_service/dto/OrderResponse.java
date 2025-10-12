package com.crio.buy_buddy_order_service.dto;
import java.time.LocalDateTime;
import java.util.List;

import com.crio.buy_buddy_order_service.model.Order;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderResponse {
    private Long id;
    private LocalDateTime orderDate;
    private Long customerId;
    private List<Long> productIds;
    private Customer customer; // Fetched customer details
    private List<Product> products; // Fetched product details

    public OrderResponse(Order order , Customer customer, List<Product> products) {
        this.id = order.getId();
        this.orderDate = order.getOrderDate();
        this.customerId = order.getCustomerId();
        this.productIds = order.getProductIds();
        this.customer = customer;
        this.products = products;
    }
}