package com.example.dto;

import lombok.Data;

import java.util.List;

@Data
public class CartResponse
{
    private Long userId;
    private List<CartItemDto> items;
}
