package com.example.service;

import com.example.model.InventoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.repository.InventoryRepository;

import java.util.concurrent.locks.ReentrantLock;

@Service
public class InventoryService
{
    @Autowired
    private InventoryRepository inventoryRepository;

    //implemented this to prevent race conditions
    private final ReentrantLock lock = new ReentrantLock();

    /// Deducting the stock
    public boolean reduceStock(Long productId, int quantity)
    {
        lock.lock();
        try
        {
           InventoryEntity inventoryEntity =
              inventoryRepository.findByProductId(productId)
                     .orElseThrow(()->new RuntimeException("Product not found in id"));

            if (inventoryEntity.getAvailableQuantity() < quantity)
            {
                return false;
            }

            inventoryEntity.setAvailableQuantity
                    (inventoryEntity.getAvailableQuantity() - quantity);

            inventoryRepository.save(inventoryEntity);

            return true;
        }
        finally
        {
           lock.unlock();
        }
    }

    /// Restoring the stock using saga rollback
    public void restoreStock(Long productId, int quantity)
    {
        lock.lock();
        try
        {
         InventoryEntity inventoryEntity =
                 inventoryRepository.findByProductId(productId)
                 .orElseThrow(()->new RuntimeException("product not found"));

         inventoryEntity.setAvailableQuantity
                 (inventoryEntity.getAvailableQuantity() + quantity);

         inventoryRepository.save(inventoryEntity);
        }
        finally
        {
          lock.unlock();
        }
    }
}
