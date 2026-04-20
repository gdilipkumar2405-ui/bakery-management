package com.example.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.util.pattern.PathPattern;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "payment")
public class PaymentEntity
{
    @Id
    @GeneratedValue
    private Long id;

    private Long orderId;

    private double amount;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    private String status;

    private LocalDateTime createdAt;
}
