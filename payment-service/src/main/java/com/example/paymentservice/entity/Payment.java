package com.example.paymentservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cardNumber;
    private double amount;
    private String message;
    private String status;
    private String transactionType;
    private Long orderId;
    private Long userId;

    public Payment(String cardNumber, double amount, String message, String status, String transactionType, Long orderId, Long userId) {
        this.cardNumber = cardNumber;
        this.amount = amount;
        this.message = message;
        this.status = status;
        this.transactionType = transactionType;
        this.orderId = orderId;
        this.userId = userId;
    }
}
