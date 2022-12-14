package com.example.paymentservice.service;

import com.example.paymentservice.model.PaymentRequest;
import com.example.paymentservice.model.PaymentResponse;

public interface PaymentService {
    PaymentResponse processPayment(String authorizationHeader, PaymentRequest paymentRequest);
}
