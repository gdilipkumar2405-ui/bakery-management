package com.example.service;

import com.example.config.CartClient;
import com.example.config.KafkaProducer;
import com.example.dto.OrderResponse;
import com.example.dto.PlaceOrderRequest;
import com.example.model.OrderEntity;
import com.example.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderService
{
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private KafkaProducer kafkaProducer;

    @Autowired
    private CartClient cartClient;
    /// place order
    public OrderResponse placeOrder(PlaceOrderRequest request)
    {
        /// fetch the cart
        Object cart = cartClient.getCart(request.getUserId());

        /// create order
        OrderEntity orderEntity = new OrderEntity();

        orderEntity.setUserId(request.getUserId());
        orderEntity.setStatus("Order Created");
        orderEntity.setCreatedAt(LocalDateTime.now());

        orderEntity = orderRepository.save(orderEntity);

        /// trigger saga ---> Inventory
        kafkaProducer.send("order-created",orderEntity.getId().toString());

        OrderResponse response = new OrderResponse();

        response.setOrderId(orderEntity.getId());
        response.setStatus(orderEntity.getStatus());

        return response;
    }
}
