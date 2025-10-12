package com.crio.buy_buddy_order_service.service.implementation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crio.buy_buddy_order_service.exception.ResourceNotFoundException;
import com.crio.buy_buddy_order_service.feign.CustomerClient;
import com.crio.buy_buddy_order_service.feign.ProductClient;
import com.crio.buy_buddy_order_service.repository.OrderRepository;
import com.crio.buy_buddy_order_service.service.OrderService;
import com.crio.buy_buddy_order_service.dto.Customer;
import com.crio.buy_buddy_order_service.dto.OrderRequest;
import com.crio.buy_buddy_order_service.dto.OrderResponse;
import com.crio.buy_buddy_order_service.dto.Product;
import com.crio.buy_buddy_order_service.model.Order;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerClient customerClient;

    @Autowired
    private ProductClient productClient;

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public OrderResponse getOrderById(Long id) {
        Order order =  orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
        Customer customer = customerClient.getCustomerById(order.getCustomerId()).getBody();
        List<Product> products = order.getProductIds().stream()
           .map(productId -> {
              return productClient.getProductById(productId).getBody();
           }).collect(Collectors.toList());

        return new OrderResponse(order, customer, products);
    }

    @Override
    public Order createOrder(OrderRequest orderRequest) {
        // Customer customer = customerRepository.findById(orderRequest.getCustomerId())
        //         .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + orderRequest.getCustomerId()));
        // List<Product> products = productRepository.findAllById(orderRequest.getProductIds());
        
        // if (products.size() != orderRequest.getProductIds().size()) {
        //     throw new ResourceNotFoundException("One or more products not found");
        // }

        Order order = new Order();

        order.setCustomerId(orderRequest.getCustomerId());
        order.setProductIds(orderRequest.getProductIds());
        order.setOrderDate(LocalDateTime.now());
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));

        orderRepository.delete(order);
    }
}