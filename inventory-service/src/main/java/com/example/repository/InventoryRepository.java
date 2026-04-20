package com.example.repository;

import com.example.model.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryEntity, Long>
{
    Optional<InventoryEntity> findByProductId(Long productId);
}
