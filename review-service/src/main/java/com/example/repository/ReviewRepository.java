package com.example.repository;

import com.example.model.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long>
{
    Optional<ReviewEntity> findByUserIdAndProductId(Long userId,Long productId);

    List<ReviewEntity> findByProductId(Long productId);
}
