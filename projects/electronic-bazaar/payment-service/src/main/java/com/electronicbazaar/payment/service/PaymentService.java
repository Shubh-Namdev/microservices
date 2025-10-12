package com.electronicbazaar.payment.service;

import com.electronicbazaar.payment.model.Payment;
import com.electronicbazaar.payment.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class PaymentService {
    private final PaymentRepository repo;

    public PaymentService(PaymentRepository repo) {
        this.repo = repo;
    }

    public Payment create(Payment p) {
        p.setTransactionId(java.util.UUID.randomUUID().toString());
        p.setStatus("SUCCESS");
        p.setCreatedAt(Instant.now());
        return repo.save(p);
    }

    public Payment get(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Payment not found"));
    }
}
