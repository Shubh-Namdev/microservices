package com.electronicbazaar.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private Order order;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private com.electronicbazaar.model.enums.PaymentStatus paymentStatus;

    private String transactionId;
}
