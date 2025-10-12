package com.electronicbazaar.service;

import com.electronicbazaar.dto.CustomerDTO;
import com.electronicbazaar.model.Customer;
import com.electronicbazaar.repository.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private final CustomerRepository repository;
    private final ObjectMapper objectMapper;

    public CustomerService(CustomerRepository repository, ObjectMapper objectMapper) {
        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    public CustomerDTO create(Customer customer) {
        if (customer.getRole() == null || customer.getRole() == "")
            customer.setRole("ROLE_USER");
        return objectMapper.convertValue(repository.save(customer), CustomerDTO.class);
    }

    public CustomerDTO getById(Long id) {
        Customer customer = repository.findById(id).orElseThrow();

        return objectMapper.convertValue(customer, CustomerDTO.class);
    }

    public List<CustomerDTO> getAll() {
        List<Customer> customers = repository.findAll();
         
        return customers.stream()
           .map(c -> objectMapper.convertValue(c, CustomerDTO.class))
           .collect(Collectors.toList());
    }
}
