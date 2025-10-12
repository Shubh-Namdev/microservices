package com.electronicbazaar.order.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Customer {
    private Long id;
    private String email;
    private String name;
    private String address;
}
