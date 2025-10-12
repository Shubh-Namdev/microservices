package com.crio.buy_buddy_order_service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.crio.buy_buddy_order_service.dto.Product;

@FeignClient("buy-buddy-product-service")
public interface ProductClient {

    @GetMapping("api/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id);
}
