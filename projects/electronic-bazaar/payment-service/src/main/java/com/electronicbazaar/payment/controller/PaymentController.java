package com.electronicbazaar.payment.controller;

import com.electronicbazaar.payment.model.Payment;
import com.electronicbazaar.payment.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.HttpHeaders;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    private final PaymentService service;
    private final WebClient webClient;

    public PaymentController(PaymentService service, WebClient.Builder webClientBuilder) {
        this.service = service;
        this.webClient = webClientBuilder.baseUrl(System.getenv().getOrDefault("ORDER_SERVICE_URL","http://order-service:8084")).build();
    }

    @PostMapping
    public ResponseEntity<Payment> create(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader, @RequestBody Payment p) {
        // Validate order exists by calling order-service
        try {
            webClient.get()
                .uri("/orders/{id}", p.getOrderId())
                .header(HttpHeaders.AUTHORIZATION, authHeader)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        } catch (Exception ex) {
            throw new RuntimeException("Order validation failed: " + ex.getMessage());
        }
        return ResponseEntity.ok(service.create(p));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> get(@PathVariable Long id) { return ResponseEntity.ok(service.get(id)); }
}
