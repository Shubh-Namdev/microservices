package com.electronicbazaar.product.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
    private Long id;

    @NotBlank
    private String name;

    @Size(max = 2000)
    private String description;

    @NotNull @PositiveOrZero
    private Double price;

    @NotNull @Min(0)
    private Integer stockQuantity;
}
