package com.example.service;

import com.example.dto.PaymentRequest;
import com.example.model.PaymentEntity;
import com.example.model.PaymentMethod;
import com.example.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class PaymentService
{
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private PaymentRepository repository;

    public void processPayment(PaymentRequest request)
    {
        PaymentMethod method = PaymentMethod.valueOf(request.getPaymentMethod());

        boolean success;

        // 🔥 DIFFERENT LOGIC BASED ON METHOD
        switch (method) {

            case CASH_ON_DELIVERY:
                success = true; // always success
                break;

            case UPI:
                success = new Random().nextInt(100) < 90; // 90% success
                break;

            case CREDIT_CARD:
            case DEBIT_CARD:
                success = new Random().nextInt(100) < 80; // 80% success
                break;

            default:
                success = false;
        }

        // Save payment
        PaymentEntity payment = new PaymentEntity();
        payment.setOrderId(request.getOrderId());
        payment.setAmount(request.getAmount());
        payment.setPaymentMethod(PaymentMethod.valueOf(request.getPaymentMethod()));
        payment.setStatus(success ? "SUCCESS" : "FAILED");
        payment.setCreatedAt(LocalDateTime.now());

        repository.save(payment);

        // Publish event
        if (success) {
            kafkaTemplate.send("payment-success", request.getOrderId().toString());
        } else {
            kafkaTemplate.send("payment-failed", request.getOrderId().toString());
        }
    }
}

