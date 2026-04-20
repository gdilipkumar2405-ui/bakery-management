package com.example.dto;

import lombok.Data;

@Data
public class ProductResponse
{
    private Long id;
    private String name;
    private double price;
    private String description;
    private boolean available;
}
