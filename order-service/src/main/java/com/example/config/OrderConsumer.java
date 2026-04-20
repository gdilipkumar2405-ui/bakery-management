package com.example.config;

import com.example.model.OrderEntity;
import com.example.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer
{
    @Autowired
    private OrderRepository orderRepository;

    // ✅ Payment Success → Confirm Order
    @KafkaListener(topics = "payment-success", groupId = "order-group")
    public void handlePaymentSuccess(String orderId)
    {
        OrderEntity order = orderRepository.findById(Long.valueOf(orderId))
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus("CONFIRMED");
        orderRepository.save(order);

        System.out.println("Order CONFIRMED: " + orderId);
    }

    // ❌ Payment Failed → Mark Order Failed
    @KafkaListener(topics = "payment-failed", groupId = "order-group")
    public void handlePaymentFailure(String orderId)
    {
        OrderEntity order = orderRepository.findById(Long.valueOf(orderId))
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus("FAILED");
        orderRepository.save(order);

        System.out.println("Order FAILED: " + orderId);
    }

    /// This is incase of inventory failure
    @KafkaListener(topics = "inventory-failed", groupId = "order-group")
    public void handleInventoryFailure(String orderId)
    {
        OrderEntity order = orderRepository.findById(Long.valueOf(orderId))
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus("FAILED");
        orderRepository.save(order);
    }
}
