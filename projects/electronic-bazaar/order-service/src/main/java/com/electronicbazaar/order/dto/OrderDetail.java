package com.electronicbazaar.order.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Data;
import reactor.core.publisher.Mono;

@Data
@Builder
public class OrderDetail {
    private Long id;
    private LocalDateTime orderDate;
    private Double totalAmount;
    private Mono<Customer> customer;
    private List<ItemDetails> items;
}
