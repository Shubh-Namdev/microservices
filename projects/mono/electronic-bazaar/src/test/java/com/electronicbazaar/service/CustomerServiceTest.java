package com.electronicbazaar.service;

import com.electronicbazaar.dto.CustomerDTO;
import com.electronicbazaar.model.Customer;
import com.electronicbazaar.repository.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class CustomerServiceTest {
    @Test
    public void testGetById() {
        CustomerRepository repo = Mockito.mock(CustomerRepository.class);
        CustomerService service = new CustomerService(repo, new ObjectMapper());
        Customer c = Customer.builder().id(1L).name("A").email("a@b").build();
        when(repo.findById(1L)).thenReturn(Optional.of(c));

        CustomerDTO res = service.getById(1L);
        assertEquals("A", res.getName());
    }
}
