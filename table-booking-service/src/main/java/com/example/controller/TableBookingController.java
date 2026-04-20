package com.example.controller;

import com.example.dto.BookingRequest;
import com.example.service.TableBookingService;
import jakarta.persistence.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class TableBookingController
{
    @Autowired
    private TableBookingService tableBookingService;

    @PostMapping
    public String bookTable(@RequestBody BookingRequest request)
    {
        return tableBookingService.bookTable(request);
    }
}
