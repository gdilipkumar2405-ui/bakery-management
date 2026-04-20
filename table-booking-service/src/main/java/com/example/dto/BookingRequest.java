package com.example.dto;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Data
public class BookingRequest
{
    private Long userId;
    private Integer tableNumber;
    private LocalDateTime slotNum;
    private LocalDateTime slotTime;
}
