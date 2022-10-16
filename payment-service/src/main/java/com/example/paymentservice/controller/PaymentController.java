package com.example.paymentservice.controller;

import com.example.paymentservice.model.PaymentRequest;
import com.example.paymentservice.model.PaymentResponse;
import com.example.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/process")
    public PaymentResponse processPayment(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
                                          @RequestBody PaymentRequest request) {
        return paymentService.processPayment(authorizationHeader, request);
    }
}
