package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.cloud.openfeign.EnableFeignClients;

@Entity
@Data
@Table(name = "order_item")
public class OrderItemEntity
{
    @Id
    @GeneratedValue
    private Long id;

    private Long orderId;

    private Long productId;

    private Integer quantity;

    private Double price;
}
