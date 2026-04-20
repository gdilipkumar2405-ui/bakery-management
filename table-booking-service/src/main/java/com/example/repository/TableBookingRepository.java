package com.example.repository;

import com.example.model.TableBookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface TableBookingRepository extends JpaRepository<TableBookingEntity, Long>
{
    Optional<TableBookingEntity> findByTableNumberAndSlotTime
            (
                    Integer tableNumber,
                    LocalDateTime slotTime
            );
}
