package com.example.orderservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String transactionType;
    private Long userId;
    private double totalAmount;
    private String status;

    public Order(String transactionType, Long userId, double totalAmount, String status) {
        this.transactionType = transactionType;
        this.userId = userId;
        this.totalAmount = totalAmount;
        this.status = status;
    }
}
