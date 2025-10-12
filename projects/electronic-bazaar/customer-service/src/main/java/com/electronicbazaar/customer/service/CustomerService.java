package com.electronicbazaar.customer.service;

import com.electronicbazaar.customer.model.Customer;
import com.electronicbazaar.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository repo;
    public CustomerService(CustomerRepository repo) { this.repo = repo; }
    public Customer save(Customer c) { return repo.save(c); }
    public Customer get(Long id) { return repo.findById(id).orElseThrow(() -> new RuntimeException("Customer not found")); }
    public List<Customer> list() { return repo.findAll(); }
    public void delete(Long id) { repo.deleteById(id); }
}
