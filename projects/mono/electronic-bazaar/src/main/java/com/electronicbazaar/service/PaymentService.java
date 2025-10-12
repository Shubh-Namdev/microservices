package com.electronicbazaar.service;

import com.electronicbazaar.model.Payment;
import com.electronicbazaar.repository.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private final PaymentRepository repo;

    public PaymentService(PaymentRepository repo) {
        this.repo = repo;
    }

    public Payment save(Payment p) { return repo.save(p); }
}
