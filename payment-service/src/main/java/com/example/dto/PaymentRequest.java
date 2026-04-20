package com.example.dto;

import lombok.Data;

@Data
public class PaymentRequest
{
    private Long orderId;
    private double amount;
    private String paymentMethod; //UPI,CREDITCARD,DEBITCARD.
}
