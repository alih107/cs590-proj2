package com.example.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerifyDto {

    private boolean success;
    private int userId;
    private String username;

    private String firstName;
    private String lastName;
    private String email;
    private String shippingAddress;
    private String paymentMethod;
    private String role;
}
