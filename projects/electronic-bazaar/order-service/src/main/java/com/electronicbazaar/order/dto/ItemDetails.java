package com.electronicbazaar.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ItemDetails {
    private Long id;
    private String productName;
    private Integer quantity;
    private Double price;
    private Double itemTotalPrice;
}
