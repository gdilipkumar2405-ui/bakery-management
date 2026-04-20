package com.example.service;

import com.example.dto.BookingRequest;
import com.example.model.TableBookingEntity;
import com.example.repository.TableBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.locks.ReentrantLock;

@Service
public class TableBookingService
{
    @Autowired
    private TableBookingRepository repository;

    private final ReentrantLock lock = new ReentrantLock();

    public String bookTable(BookingRequest request)
    {
        lock.lock();
        try
        {
            repository.findByTableNumberAndSlotTime(
                    request.getTableNumber(),
                    request.getSlotTime()
            ).ifPresent(b -> {
                throw new RuntimeException("Table already booked for this slot");
            });

            TableBookingEntity booking = new TableBookingEntity();
            booking.setUserId(request.getUserId());
            booking.setTableNumber(request.getTableNumber());
            booking.setSlotTime(request.getSlotTime());
            booking.setStatus("BOOKED");

            repository.save(booking);

            return "Table booked successfully";
        }
        finally
        {
        lock.unlock();
        }
    }
}
