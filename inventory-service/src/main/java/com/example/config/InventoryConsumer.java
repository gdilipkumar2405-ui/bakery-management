package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.example.service.InventoryService;

@Service
public class InventoryConsumer
{
    @Autowired
    private InventoryService inventoryService;

    @KafkaListener(topics = "order-created", groupId = "inventory-group")
    public void handleOrder(String orderId)
    {
        //for now, it simulates
        System.out.println("Processing Order :" + orderId);

        //Later : --> reduces stock(...) ----> publish success or failure event
    }
}
