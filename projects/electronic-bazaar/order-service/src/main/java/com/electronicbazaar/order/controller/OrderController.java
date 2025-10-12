package com.electronicbazaar.order.controller;

import com.electronicbazaar.order.dto.OrderDetail;
import com.electronicbazaar.order.model.Order;
import com.electronicbazaar.order.service.OrderService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody Order o) {
        // In a full impl: validate product availability via product-service
        return ResponseEntity.ok(service.create(o));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDetail> get(@PathVariable Long id, @AuthenticationPrincipal Jwt jwt) {
        
        return ResponseEntity.ok(service.get(id, jwt.getTokenValue()));
    }
    
}
