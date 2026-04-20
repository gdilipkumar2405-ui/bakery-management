package com.example.controller;

import com.example.dto.ReviewRequest;
import com.example.model.ReviewEntity;
import com.example.service.ReviewService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController
{
    private final ReviewService service;

    public ReviewController(ReviewService service)
    {
        this.service = service;
    }

    @PostMapping
    public String addReview(@RequestBody ReviewRequest request)
    {
        service.addReview(request);
        return "Review added";
    }

    @GetMapping("/{productId}")
    public List<ReviewEntity> getReviews(@PathVariable Long productId)
    {
        return service.getReviews(productId);
    }

    @GetMapping("/avg/{productId}")
    public double getAverage(@PathVariable Long productId)
    {
        return service.getAverageRating(productId);
    }
}