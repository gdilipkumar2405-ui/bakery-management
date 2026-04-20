package com.example.controller;

import com.example.dto.OrderResponse;
import com.example.dto.PlaceOrderRequest;
import com.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController
{
    @Autowired
    private OrderService orderService;

    @PostMapping
    public OrderResponse placeOrder(@RequestBody PlaceOrderRequest orderRequest)
    {
        return orderService.placeOrder(orderRequest);
    }
}
