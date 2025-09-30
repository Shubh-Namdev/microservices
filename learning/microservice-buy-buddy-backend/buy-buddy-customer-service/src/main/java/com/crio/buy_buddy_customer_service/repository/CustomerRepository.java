package com.crio.buy_buddy_customer_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crio.buy_buddy_customer_service.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Custom query method
    List<Customer> findByEmail(String email);
}