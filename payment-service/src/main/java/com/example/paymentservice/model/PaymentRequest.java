package com.example.paymentservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {
    private String cardNumber;
    private double amount;
    private String transactionType;
    private Long orderId;
    private Long userId;
}
