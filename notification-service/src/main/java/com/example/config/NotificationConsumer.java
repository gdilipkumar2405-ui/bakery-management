package com.example.config;

import com.example.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer
{
    @Autowired
    private NotificationService service;

    @KafkaListener(topics = "payment-success", groupId = "notification-group")
    public void paymentSuccess(String orderId)
    {
        service.sendNotification(
                "Payment successful for Order " + orderId,
                "EMAIL"
        );
    }

    @KafkaListener(topics = "payment-failed", groupId = "notification-group")
    public void paymentFailed(String orderId)
    {
        service.sendNotification(
                "Payment failed for Order " + orderId,
                "SMS"
        );
    }

    @KafkaListener(topics = "order-created", groupId = "notification-group")
    public void orderCreated(String orderId)
    {
        service.sendNotification(
                "Order placed successfully: " + orderId,
                "PUSH"
        );
    }

}
