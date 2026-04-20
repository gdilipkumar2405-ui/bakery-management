package com.example.service;

import com.example.model.NotificationEntity;
import com.example.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificationService
{
    @Autowired
    private NotificationRepository repository;

    public void sendNotification(String message, String type) {

        // 🔥 Simulate sending
        System.out.println("Sending " + type + ": " + message);

        NotificationEntity entity = new NotificationEntity();
        entity.setMessage(message);
        entity.setType(type);
        entity.setCreatedAt(LocalDateTime.now());

        repository.save(entity);
    }

}
