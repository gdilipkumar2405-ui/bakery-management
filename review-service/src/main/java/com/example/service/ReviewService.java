package com.example.service;

import com.example.dto.ReviewRequest;
import com.example.model.ReviewEntity;
import com.example.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewService
{
    @Autowired
    private ReviewRepository repository;

    // ✅ Add Review
    public void addReview(ReviewRequest request)
    {
        repository.findByUserIdAndProductId(
                request.getUserId(),
                request.getProductId()
        ).ifPresent(r -> {
            throw new RuntimeException("Review already exists");
        });

        ReviewEntity review = new ReviewEntity();

        review.setUserId(request.getUserId());
        review.setProductId(request.getProductId());
        review.setRating(request.getRating());
        review.setComment(request.getComment());
        review.setCreatedAt(LocalDateTime.now());

        repository.save(review);
    }

    // ✅ Get Reviews
    public List<ReviewEntity> getReviews(Long productId)
    {
        return repository.findByProductId(productId);
    }

    // ✅ Average Rating
    public double getAverageRating(Long productId)
    {
        List<ReviewEntity> reviews = repository.findByProductId(productId);

        return reviews.stream()
                .mapToInt(ReviewEntity::getRating)
                .average()
                .orElse(0.0);
    }
}
