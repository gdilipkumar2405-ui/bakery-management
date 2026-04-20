package com.example.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.time.LocalDateTime;

@Entity
@Table(name = "table-booking", uniqueConstraints =
                   @UniqueConstraint(columnNames = {"tableNumber","slotTime"}))
@Data
public class TableBookingEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Integer tableNumber;

    private LocalDateTime slotTime;

    private String status;// BOOKED / CANCELLED
}
