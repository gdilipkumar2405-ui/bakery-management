package com.example.model;

import jakarta.persistence.*;
import lombok.Data;
import org.apache.kafka.common.protocol.types.Field;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "notification")
public class NotificationEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    private String type; ///EMAIL --> SMS --> PUSH

    private LocalDateTime createdAt;
}
