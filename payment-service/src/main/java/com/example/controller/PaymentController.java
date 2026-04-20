package com.example.controller;

import com.example.dto.PaymentRequest;
import com.example.service.PaymentService;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController
{
        @Autowired
        private PaymentService service;

        @PostMapping
        public String pay(@RequestBody PaymentRequest request)
        {
            service.processPayment(request);
            return "Payment processing started";
        }
    }


