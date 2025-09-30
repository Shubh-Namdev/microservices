package com.crio.buy_buddy_customer_service.service;

import java.util.List;
import com.crio.buy_buddy_customer_service.model.Customer;

public interface CustomerService {
    List<Customer> getAllCustomers();
    Customer getCustomerById(Long id);
    Customer createCustomer(Customer customer);
    Customer updateCustomer(Long id, Customer customerDetails);
    void deleteCustomer(Long id);
}
