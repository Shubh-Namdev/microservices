package com.crio.buy_buddy_order_service.dto;
import lombok.Data;

@Data
public class Customer {
    private Long id;
    private String name;
    private String email;
}