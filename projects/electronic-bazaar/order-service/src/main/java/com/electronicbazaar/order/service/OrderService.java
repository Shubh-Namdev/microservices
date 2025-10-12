package com.electronicbazaar.order.service;

import com.electronicbazaar.order.dto.Customer;
import com.electronicbazaar.order.dto.ItemDetails;
import com.electronicbazaar.order.dto.OrderDetail;
import com.electronicbazaar.order.dto.Product;
import com.electronicbazaar.order.model.Order;
import com.electronicbazaar.order.repository.OrderRepository;

import jakarta.transaction.Transactional;
import reactor.core.publisher.Mono;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository repo;
    private final WebClient customerWebClient;
    private final WebClient productWebClient;

    public OrderService(OrderRepository repo, WebClient customerWebClient, WebClient productWebClient) {
        this.repo = repo;
        this.customerWebClient = customerWebClient;
        this.productWebClient = productWebClient;
    }

    public Order create(Order o) {
        o.setOrderDate(LocalDateTime.now());
        return repo.save(o);
    }

    @Transactional
    public OrderDetail get(Long id, String token) {
        Order order = repo.findById(id)
                        .orElseThrow(() -> new RuntimeException("Order not found"));
        Mono<Customer> customer = customerWebClient.get()
                                .uri("/customers/{id}", order.getCustomerId())
                                .header("Authorization", token)
                                .retrieve()
                                .bodyToMono(Customer.class);

        List<ItemDetails> items = order.getItems().stream()
            .map(oi -> {
               ItemDetails item = ItemDetails.builder()
                                       .id(oi.getId()).quantity(oi.getQuantity())
                                       .price(oi.getPrice())
                                       .itemTotalPrice(oi.getQuantity() * oi.getPrice())
                                       .build();
                Mono<Product> product = productWebClient.get()
                                     .uri("/products/{id}", oi.getProductId())
                                     .header("Authorization", token)
                                     .retrieve()
                                     .bodyToMono(Product.class);

                item.setProductName(product.map(Product::getName).toString());
                return item;

            }).collect(Collectors.toList());


            return OrderDetail.builder()
                      .id(order.getId())
                      .orderDate(order.getOrderDate())
                      .totalAmount(5000.00)
                      .customer(customer)
                      .items(items)
                      .build();
            
    }
}
