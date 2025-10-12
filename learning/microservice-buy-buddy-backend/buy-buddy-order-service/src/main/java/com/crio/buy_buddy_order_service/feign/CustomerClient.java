package com.crio.buy_buddy_order_service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.crio.buy_buddy_order_service.dto.Customer;

@FeignClient("buy-buddy-customer-service")
public interface CustomerClient {

    @GetMapping("api/customers/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id);
}
