package com.electronicbazaar.controller;

import com.electronicbazaar.model.Payment;
import com.electronicbazaar.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    private final PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Payment> create(@RequestBody Payment p) {
        return ResponseEntity.ok(service.save(p));
    }
}
