package com.example.config;

import com.example.dto.PaymentRequest;
import com.example.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PaymentConsumer
{
    @Autowired
    private PaymentService paymentService;

    @KafkaListener(topics = "inventory-success", groupId = "payment-group")
    public void consume(String orderId)
    {
        PaymentRequest request = new PaymentRequest();
        request.setOrderId(Long.valueOf(orderId));

        paymentService.processPayment(request);
    }
}
