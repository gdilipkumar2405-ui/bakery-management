package com.example.dto;

import lombok.Data;

@Data
public class ReviewRequest
{
    private Long userId;
    private Long productId;
    private Integer rating;
    private String comment;
}
